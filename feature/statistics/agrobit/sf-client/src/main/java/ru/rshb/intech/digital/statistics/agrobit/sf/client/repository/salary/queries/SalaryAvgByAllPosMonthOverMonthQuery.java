package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.queries;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Persistence;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.SfctSalaryCategoryStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.AgroCategories;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.KpiParams;

import java.util.List;

@Component
public class SalaryAvgByAllPosMonthOverMonthQuery {

  private static final String SQL =
      """
      select
      	report_dt,
      	indicator_val,
      	coalesce(round((indicator_val - lag(indicator_val) over (order by report_dt)) / indicator_val * 100), 0) as indicator_diff,
      	:catName as category_name
      from (
      	select
      		da.report_dt,
      		round(avg(kpi_cnt)) as indicator_val
      	from dm.sfct_digital_analytics_daily da
      	where (kpi_nm = :kpiParamName)
      	group by da.report_dt
      	order by da.report_dt
      ) as analytic_data;
      """;

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final SalaryAvgCategoryStatsRowMapper rowMapper;

  public SalaryAvgByAllPosMonthOverMonthQuery(
      @Qualifier(Persistence.GREENPLUM_STATS_JDBC_TEMPLATE_BEAN) NamedParameterJdbcTemplate jdbcTemplate,
      SalaryAvgCategoryStatsRowMapper rowMapper
  ) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
  }

  @Nonnull
  public List<SfctSalaryCategoryStatsEntry> execute() {

    final var paramMapper = new MapSqlParameterSource("kpiParamName", KpiParams.AVG_SALARY_BY_PROFESSION)
        .addValue("catName", AgroCategories.ALL);

    try {
      return jdbcTemplate.query(SQL, paramMapper, rowMapper);
    } catch (Exception e) {
      throw new RuntimeException("Agrobit stats fetching error", e);
    }
  }
}
