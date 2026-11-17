package ru.rshb.intech.digital.vacancies.hh.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class HHAuth {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("token_type")
  private String tokenType;


  public String getTokenTypeCapitalized() {
    return StringUtils.capitalize(tokenType);
  }
}
