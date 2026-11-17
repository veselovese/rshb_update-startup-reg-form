package ru.rshb.intech.digital.references.universities.loader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import jakarta.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.startsWithAny;

public class RaooSqlConverter implements RaooConverter {

  private final RaooReader reader = new RaooXmlReader();

  @Override
  public long convert(@Nonnull File inputFile, @Nonnull File outputFile) throws Exception {
    final var filter = new CertificateFilter();
    final var writer = new BufferedWriter(outputFile, filter, new CertificateMarshaller());
    final long count = reader.read(inputFile, writer);

    System.out.println("--------- Kinds -------------");
    filter.getKinds().forEach(kind -> System.out.println(kind));

    return count;
  }

  @RequiredArgsConstructor
  private static class BufferedWriter implements RaooWriter {

    private static final int BUFFER_SIZE = 100;

    private final File outputFile;
    private final Predicate<OpenData.Certificate> filter;
    private final Function<OpenData.Certificate, String> marshaller;

    private StringBuilder buffer = new StringBuilder();
    private long itemCounter = 0;

    @Override
    public void write(@Nonnull OpenData.Certificate certificate) throws IOException {

      if (!filter.test(certificate)) {
        return;
      }

      if (itemCounter <= BUFFER_SIZE) {
        buffer.append(marshaller.apply(certificate));
        itemCounter++;
        return;
      }

      flush();
    }

    private void flush() throws IOException {
      FileUtils.write(outputFile, buffer.toString(), StandardCharsets.UTF_8, true);
      itemCounter = 0;
      buffer = new StringBuilder();
    }
  }

  private static class CertificateMarshaller implements Function<OpenData.Certificate, String> {

    @Override
    public String apply(OpenData.Certificate certificate) {
      return String.format("INSERT INTO digital_refs.university (external_id, full_title, short_title) VALUES ('%s', '%s', '%s');\n",
                           certificate.getId(),
                           certificate.getEduOrgFullName(),
                           certificate.getEduOrgShortName()
      );
    }
  }

  private static class CertificateFilter implements Predicate<OpenData.Certificate> {

    private static final String NOT_ACTIVE = "Недействующее";
    private static final String[] HIGH_EDUCATION = {
        "во",
        "высшее образование"
    };

    @Getter
    private final Set<String> kinds = new HashSet<>();

    @Override
    public boolean test(OpenData.Certificate certificate) {

      if (NOT_ACTIVE.equalsIgnoreCase(certificate.getStatusName())) {
        return false;
      }

      kinds.add(certificate.getActualEducationOrganization().getKindName());

      for (final var supplement : certificate.getSupplements()) {

        for (final var eduProgram : supplement.getEducationalPrograms()) {

          if (StringUtils.equals(eduProgram.getIsCanceled(), "1") || StringUtils.equals(eduProgram.getIsSuspended(), "1")) {
            continue;
          }

          if (startsWithAny(eduProgram.getEduLevelName().toLowerCase(), HIGH_EDUCATION)) {
            if (isNotBlank(eduProgram.getIsSuspended()) || isNotBlank(eduProgram.getIsCanceled())) {
              System.out.println();
            }
            return true;
          }
        }
      }

      return false;
    }
  }
}
