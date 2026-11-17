package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TourismStatsType {

  AVG_CHECK("Средний чек на человека"),

  AVG_TOUR_PRICE("Средняя стоимость тура"),

  TOUR_NUMBER("Количество представленных туров");

  private final String kpiParamName;
}
