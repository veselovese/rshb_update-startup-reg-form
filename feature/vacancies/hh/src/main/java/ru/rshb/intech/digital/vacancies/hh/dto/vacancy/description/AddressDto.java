package ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
public class AddressDto {

  private String city;
  private String street;
  private String building;
  private String description;

  @JsonProperty("lat")
  private Double latitude;

  @JsonProperty("lng")
  private Double longitude;

  @JsonProperty("metro_stations")
  private List<MetroStationDto> metroStations;

}
