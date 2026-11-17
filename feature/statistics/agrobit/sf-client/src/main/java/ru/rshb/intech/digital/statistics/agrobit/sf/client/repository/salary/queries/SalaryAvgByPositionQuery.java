package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.queries;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Persistence;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.SfctSalaryPositionStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.KpiParams;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz.firstDateOfYear;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz.lastDateOfYear;

@Component
public class SalaryAvgByPositionQuery {

  private static final String SQL =
      """
      select
      	report_dt,
      	:catName as cat_name,
      	:posName as pos_name,
      	indicator_val,
      	coalesce(indicator_val - lag(indicator_val) over (order by report_dt), 0) as indicator_diff,
      	case
      		when (indicator_val is null or indicator_val = 0) then 0
      		when (indicator_val > 0 and lag(indicator_val) over (order by report_dt) = 0) then 100
      		when (lag(indicator_val) over (order by report_dt) <> 0) then coalesce(round((indicator_val / lag(indicator_val) over (order by report_dt) - 1) * 100), 0)
      		else 0
      	end indicator_diff_pct
      from (
      	select
      		ser.report_dt as report_dt,
      		coalesce(sd.indicator_val, 0) as indicator_val
      	from
      		(
      			select
      				report_dt::date
      			from
      				pg_catalog.generate_series(:startDate, :endDate, '1 month'::interval) report_dt
      		) ser
      		left join (
      			select
      				da.report_dt,
      				round(avg(kpi_cnt)) as indicator_val
      			from
      				dm.sfct_digital_analytics_daily da
      			where
      				(kpi_nm = :kpiParamName)
      				and (position_nm in (:catPositions))
      			group by
      				da.report_dt
      			order by
      				da.report_dt
      		) sd
      		on (sd.report_dt = ser.report_dt)
      	order by ser.report_dt
      ) as anal;
      """;

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final SalaryPositionStatsRowMapper rowMapper;

  public SalaryAvgByPositionQuery(@Qualifier(Persistence.GREENPLUM_STATS_JDBC_TEMPLATE_BEAN) NamedParameterJdbcTemplate jdbcTemplate,
                                  SalaryPositionStatsRowMapper rowMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
  }

  @Nonnull
  public List<SfctSalaryPositionStatsEntry> executeWith(@Nonnull Params params) {

    final var paramMapper = new MapSqlParameterSource("kpiParamName", KpiParams.AVG_SALARY_BY_PROFESSION)
        .addValue("catName", params.getCategory())
        .addValue("posName", params.getPosition())
        .addValue("startDate", firstDateOfYear(params.getStartYear()))
        .addValue("endDate", lastDateOfYear())
        .addValue("catPositions", List.of(params.getPosition()));

    try {
      return jdbcTemplate.query(SQL, paramMapper, rowMapper);
    } catch (Exception e) {
      throw new RuntimeException("Agrobit stats fetching error", e);
    }
  }

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Params {

    private int startYear;
    private String category;
    private String position;
  }
}
