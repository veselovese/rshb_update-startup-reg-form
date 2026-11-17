package ru.rshb.intech.digital.feature.internship.core.service.statistics.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthData {

  private int year;
  private int month;
  private String field;
  private long amount;
  private double difference;

  public MonthData(int year, int month, String field) {
    this.year = year;
    this.month = month;
    this.field = field;
  }
}
