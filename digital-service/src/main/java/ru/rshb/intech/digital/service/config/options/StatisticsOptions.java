package ru.rshb.intech.digital.service.config.options;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class StatisticsOptions {

  @Value("${statistics.collection.enabled}")
  private Boolean collectionEnabled;

  @Value("${statistics.collection.cron}")
  private String collectionCron;

  private final AgrobitSalaryStatsCollectionOptions agrobitSalary;
  private final AgrobitTourismStatsCollectionOptions agrobitTourism;

  public boolean isCollectionDisabled() {
    return !collectionEnabled;
  }

  @Getter
  @Component
  public static class AgrobitSalaryStatsCollectionOptions {

    @Value("${statistics.collection.agrobit.salary.enabled}")
    private Boolean collectionEnabled;

    public boolean isCollectionDisabled() {
      return !collectionEnabled;
    }
  }

  @Getter
  @Component
  public static class AgrobitTourismStatsCollectionOptions {

    @Value("${statistics.collection.agrobit.tourism.enabled}")
    private Boolean collectionEnabled;

    public boolean isCollectionDisabled() {
      return !collectionEnabled;
    }
  }
}
