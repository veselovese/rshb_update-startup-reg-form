package ru.rshb.intech.digital.commons.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Persistence { // TODO вынести конфигурационные параметры в отдельный модуль

  /**
   * JDBC Template для подключения к БД Digital.
   */
  public static final String DEFAULT_DB_JDBC_TEMPLATE_BEAN = "digitalNamedJdbcTemplate";

  /**
   * JDBC Template для подключения к БД Greenplum со статистикой по отраслям.
   */
  public static final String GREENPLUM_STATS_JDBC_TEMPLATE_BEAN = "agrobitJdbcTemplate";

}
