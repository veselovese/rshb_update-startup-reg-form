package ru.rshb.intech.digital.references.cities.okato.loader.reader;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.rshb.intech.digital.references.cities.okato.loader.OkatoCode;
import ru.rshb.intech.digital.references.cities.okato.loader.OkatoItem;
import ru.rshb.intech.digital.references.cities.okato.loader.exception.OkatoLoaderException;
import ru.rshb.intech.digital.references.cities.okato.loader.writer.OkatoWriter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RequiredArgsConstructor
public class OkatoCsvReader implements OkatoReader {

  private static final Charset WIN1251 = Charset.forName("Windows-1251");

  private final OkatoWriter writer;

  @Override
  public long read(@Nonnull File localFile) {

    final var path = localFile.toPath();

    long processedItems = 0;
    OkatoItem currentRegion = null;

    try (final var reader = Files.newBufferedReader(path, WIN1251)) {

      var row = "";
      while (row != null) {

        row = reader.readLine();
        if (isBlank(row)) {
          continue;
        }

        final var currentItem = parseLine(row);
        if (currentItem == null || isMetaItem(currentItem)) {
          continue;
        }

        if (isRegionItem(currentItem.getCode())) {
          currentRegion = currentItem;
          continue;
        }

        if (isCity(currentItem)) {
          currentItem.setParent(currentRegion);
          write(currentItem);
          processedItems++;
        }
      }

      return processedItems;
    } catch (Exception e) {
      throw new OkatoLoaderException("File reading error: " + path, e);
    }
  }

  private boolean isCity(@Nonnull OkatoItem item) {

    if (isMoscowAreas(item)) {
      return false;
    }

    return isCity1(item.getCode())
           || isCity2(item.getCode())
           || isCity3(item);
  }

  private boolean isCity1(@Nonnull OkatoCode code) {
    return code.getArea().startsWith("4") && !code.getArea().endsWith("0")
           && (isEmptyCode(code.getVillageCouncil()) && isEmptyCode(code.getRuralLocality()))
           && "1".equals(code.getSection());
  }

  private boolean isCity2(@Nonnull OkatoCode code) {
    return code.getArea().startsWith("2")
        && (code.getVillageCouncil().startsWith("5") && code.getVillageCouncil().endsWith("1"))
        && isEmptyCode(code.getRuralLocality())
        && "1".equals(code.getSection());
  }

  private boolean isCity3(@Nonnull OkatoItem item) {

    if (item.getTitle().startsWith("Поселки городского типа")) {
      return false;
    }

    final var code = item.getCode();

    final var areaCode = code.getArea();
    if (isEmptyCode(areaCode)) {
      return false;
    }

    return (areaCode.charAt(0) == '4' && areaCode.charAt(1) != '0' && areaCode.charAt(2) == '0')
           && (isEmptyCode(code.getVillageCouncil()) && isEmptyCode(code.getRuralLocality()))
           && "1".equals(code.getSection());
  }

  private boolean isMoscowAreas(@Nonnull OkatoItem item) {
    return "45".equals(item.getCode().getRegion()) && isNotEmptyCode(item.getCode().getArea());

  }

  private void write(@Nonnull OkatoItem item) {
    writer.write(item);
  }

  private boolean isMetaItem(@Nonnull OkatoItem item) {
    final var code = item.getCode();
    return isEmptyCode(code.getRegion());
  }

  private boolean isRegionItem(@Nonnull OkatoCode code) {
    return isNotEmptyCode(code.getRegion())
           && isEmptyCode(code.getArea())
           && isEmptyCode(code.getVillageCouncil())
           && isEmptyCode(code.getRuralLocality())
           && "1".equals(code.getSection());
  }

  private boolean isEmptyCode(@Nonnull String code) {
    return StringUtils.equalsAny(code, "000", "00", "0", "");
  }

  private boolean isNotEmptyCode(@Nonnull String code) {
    return !isEmptyCode(code);
  }

  @Nullable
  private OkatoItem parseLine(@Nonnull String line) {

    final String[] parts = line.split(";");
    if (parts.length < 7) {
      return null;
    }

    final var code = readCode(parts);
    final var title = omitQuotes(parts[5]);
    final var additional = omitQuotes(parts[6]);

    return new OkatoItem(null, code, title, additional);
  }

  @Nonnull
  private OkatoCode readCode(@Nonnull String[] cells) {
    return new OkatoCode(
        omitQuotes(cells[0]),
        omitQuotes(cells[1]),
        omitQuotes(cells[2]),
        omitQuotes(cells[3]),
        omitQuotes(cells[4])
    );
  }

  @Nonnull
  private String omitQuotes(@Nonnull String str) {
    final var quote = "\"";
    final var trimmed = StringUtils.trim(str);
    return StringUtils.removeStart(StringUtils.removeEnd(trimmed, quote), quote);
  }
}
