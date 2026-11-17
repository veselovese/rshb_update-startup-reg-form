package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.queries;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Persistence;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.KpiParams;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Component
public class SalaryEarliestStatDateQuery {

  private static final int FALLBACK_VALUE = 0;

  private static final String SQL =
      """
      select
        min(extract(year from da.report_dt)) as min_year
      from dm.sfct_digital_analytics_daily da
      where (kpi_nm = :kpiParamName);
      """;

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public SalaryEarliestStatDateQuery(@Qualifier(Persistence.GREENPLUM_STATS_JDBC_TEMPLATE_BEAN) NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int execute() {
    final var paramMapper = new MapSqlParameterSource("kpiParamName", KpiParams.AVG_SALARY_BY_PROFESSION);
    try {
      final var result = jdbcTemplate.query(SQL, paramMapper, minYearExtractor());
      return defaultIfNull(result, FALLBACK_VALUE);
    } catch (Exception e) {
      throw new RuntimeException("Agrobit stats fetching error", e);
    }
  }

  @Nonnull
  private ResultSetExtractor<Integer> minYearExtractor() {
    return (rs) -> {
      rs.next();
      return rs.getInt("min_year");
    };
  }
}
