package ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
public class MetroStationDto {

  @JsonProperty("station_id")
  private String stationId;

  @JsonProperty("station_name")
  private String stationName;

  @JsonProperty("line_id")
  private String lineId;

  @JsonProperty("line_name")
  private String lineName;

  @JsonProperty("lat")
  private Double latitude;

  @JsonProperty("lng")
  private Double longitude;
}
