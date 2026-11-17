package ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDto {

  private String name;
  private String email;
  private List<PhoneDto> phones;
}
