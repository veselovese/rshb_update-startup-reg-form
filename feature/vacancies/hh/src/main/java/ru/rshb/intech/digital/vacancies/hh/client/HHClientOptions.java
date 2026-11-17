package ru.rshb.intech.digital.vacancies.hh.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Builder
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class HHClientOptions {

  private String userAgent;
  private String clientId;
  private String clientSecret;
  private String defaultToken;
}
