package ru.rshb.intech.digital.subscriptions.core.service.agregator.collector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataCollectorOptions {

  private LocalDate periodStartDate;
  private LocalDate periodEndDate;
}
