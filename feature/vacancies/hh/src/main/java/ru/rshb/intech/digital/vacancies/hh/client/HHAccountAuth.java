package ru.rshb.intech.digital.vacancies.hh.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class HHAccountAuth {

  private String name;
  private String secret;
  private String token;
}
