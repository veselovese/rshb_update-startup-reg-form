package ru.rshb.intech.digital.vacancies.hh.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class HHOptions {

  private List<HHAccountAuth> accounts;
}
