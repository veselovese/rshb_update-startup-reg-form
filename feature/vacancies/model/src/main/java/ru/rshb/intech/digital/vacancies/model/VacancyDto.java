package ru.rshb.intech.digital.vacancies.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;

@Schema(name = "Расширенные сведения о вакансии")
@Data
@Generated
public class VacancyDto {

  @Schema(
      description = "ID вакансии в HH",
      example = "70700508"
  )
  private String externalId;

  @Schema(
      description = "Заголовок вакансии.",
      example = "Ведущий разработчик"
  )
  private String title;

  @Schema(
      description = "Описание вакансии.",
      example = "Lorem ipsum"
  )
  private String description;

  @Schema(
      description = "Описание вакансии в формате HTML. " +
                    "Описание: https://github.com/hhru/api/blob/master/docs/vacancies.md#branded_description",
      example = """
                <style>...</style><div>...</div><script></script>
                """
  )
  private String descriptionHtml;

  @Schema(
      description = "Ссылка на вакансию.",
      example = "https://hh.ru/vacancy/8331228"
  )
  private String url;

  @Schema(
      description = "Ссылка для отклика на вакансию.",
      example = "https://hh.ru/applicant/vacancy_response?vacancyId=8331228"
  )
  private String applyUrl;

  @Schema(
      description = "Город.",
      example = "Москва"
  )
  private String city;

  @Schema(
      description = "Дата публикации с форматированием.",
      example = "29 июля 2022"
  )
  private String publishDate;
}
