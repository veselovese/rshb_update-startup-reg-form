package ru.rshb.intech.digital.feature.startups.core.service.importing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.transliteration.RuTransliterator;
import ru.rshb.intech.digital.commons.utils.transliteration.Transliterator;
import ru.rshb.intech.digital.feature.startups.dto.StartupCardDto;
import ru.rshb.intech.digital.feature.startups.dto.StartupFileDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRecord;

import java.io.File;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.equalsAnyIgnoreCase;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Коневертер данных о стартапах/компаниях в файлы в формате JSON.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonStartupConverter implements StartupConverter {

  private static final String JSON_EXT = ".json";
  private static final String[] POSITIVE_BOOL_VALUES = {"да", "yes", "true", "+"};

  private static final ObjectMapper jsonMapper = JsonMapper.builder()
      .addModule(new JavaTimeModule())
      .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .serializationInclusion(JsonInclude.Include.NON_NULL)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(SerializationFeature.INDENT_OUTPUT, true)
      .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
      .build();

  private final Transliterator transliterator = new RuTransliterator(); // TODO make bean if needed
  private final File tmpDir;

  @Nonnull
  public static StartupConverter create(@Nonnull File workDir) {
    return new JsonStartupConverter(workDir);
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public StartupFileDto convert(@Nonnull StartupRecord startupRecord) {
    final var dto = map(startupRecord);
    try {
      final var tmpFile = new File(tmpDir, newJsonFileName(dto));
      jsonMapper.writeValue(tmpFile, dto);
      return new StartupFileDto(
          newJsonFileName(dto),
          tmpFile
      );
    } catch (Exception e) {
      throw new RuntimeException("Notification message description serialization error", e);
    }
  }

  @Nonnull
  private String newJsonFileName(@Nonnull StartupCardDto dto) {
    return dto.getSlug() + JSON_EXT;
  }

  @Nonnull
  private StartupCardDto map(@Nonnull StartupRecord input) {
    final var outputDto = new StartupCardDto();
    outputDto.setId(
        newId()
    );
    outputDto.setArchived(
        archivedFromString(input.getArchieve())
    );
    outputDto.setSlug(
        slugFromTitle(input.getTitle())
    );
    outputDto.setSiteUrl(input.getSiteLink());
    outputDto.setDistrict(input.getDistrict());
    outputDto.setTitle(input.getTitle());
    outputDto.setDescription(input.getDescription());
    outputDto.setRound(input.getRound());
    outputDto.setSummary(input.getSummary());
    outputDto.setLogo(input.getLogo());
    outputDto.setFoundedYear(input.getYear());
    outputDto.setWorkersNum(input.getWorkers());
    outputDto.setCategory(input.getCategory());
    outputDto.setStage(input.getEtap());
    outputDto.setCustomersNum(input.getIntegrations());
    outputDto.setRegion(input.getRegion());
    outputDto.setCountry(input.getCountry());
    outputDto.setCity(input.getCity());
    return outputDto;
  }

  @Nonnull
  private Boolean archivedFromString(@Nonnull String value) {
    if (isBlank(value)) {
      return false;
    }
    return equalsAnyIgnoreCase(value.trim(), POSITIVE_BOOL_VALUES);
  }

  @Nonnull
  private String slugFromTitle(@Nonnull String title) {
    return transliterator.transliterate(title);
  }

  @Nonnull
  private String newId() {
    return UUID.randomUUID()
        .toString()
        .toLowerCase();
  }
}
