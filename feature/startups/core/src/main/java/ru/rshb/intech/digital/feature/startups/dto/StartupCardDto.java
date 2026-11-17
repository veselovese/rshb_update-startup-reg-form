package ru.rshb.intech.digital.feature.startups.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO для преобразования данных о стартапе/компании в JSON.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class StartupCardDto {

  public static final String DEFAULT_SCHEMA = "content/schemas/Карточка компании.schema.json";

  /**
   * Схема JSON документа для валидации админкой.
   */
  @JsonProperty("_schemaPath")
  private final String schemaPath = DEFAULT_SCHEMA;

  private String id;

  @JsonProperty("archieve")
  private Boolean archived = false;

  private String district;

  private String title;

  private String description;

  private String round;

  private String summary;

  private String logo;

  @JsonProperty("year")
  private String foundedYear;

  @JsonProperty("workers")
  private String workersNum;

  private String category;

  @JsonProperty("etap")
  private String stage;

  @JsonProperty("integrations")
  private String customersNum;

  private String region;

  private String slug;

  @JsonProperty("site_link")
  private String siteUrl;

  private String country;

  private String city;

}
