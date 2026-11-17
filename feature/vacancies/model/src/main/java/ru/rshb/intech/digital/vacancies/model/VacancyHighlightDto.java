package ru.rshb.intech.digital.vacancies.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;

@Schema(name = "Общие сведения о вакансии")
@Data
@Generated
public class VacancyHighlightDto {

  @Schema(
      description = "ID вакансии в HH",
      example = "70909077"
  )
  private String id;

  @Schema(
      description = "Заголовок вакансии.",
      example = "Ведущий разработчик"
  )
  private String title;

  @Schema(
      description = "Город.",
      example = "Москва"
  )
  private String city;

  @Schema(
      description = "Компания.",
      example = "АО Россельхозбанк"
  )
  private String company;

  @Schema(
      description = "Дата публикации с форматированием.",
      example = "29 июля 2022"
  )
  private String publishDate;

  @Schema(
      description = "URL вакансии на HH",
      example = "https://hh.ru/vacancy/70909077"
  )
  private String url;

  @Schema(
      description = "URL для отклика на вакансию на HH",
      example = "https://hh.ru/applicant/vacancy_response?vacancyId=70909077"
  )
  private String applyUrl;
}
