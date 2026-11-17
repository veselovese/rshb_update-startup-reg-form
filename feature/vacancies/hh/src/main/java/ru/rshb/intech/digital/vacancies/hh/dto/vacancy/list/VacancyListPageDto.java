package ru.rshb.intech.digital.vacancies.hh.dto.vacancy.list;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacancyListPageDto {

  private Long found;

  private Long page;

  private Long pages;

  @JsonProperty("per_page")
  private Long perPage;

  private List<VacancyItemDto> items;
}
