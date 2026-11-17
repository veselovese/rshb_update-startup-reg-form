package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Persistence;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism.TourismAvgStatsEntry;

import java.util.List;

@Component
public class TourismAvgStatsQuery {

  private static final String SQL =
      """
      select
      	report_dt,
      	:tourismType as tourism_type,
      	indicator_val,
      	coalesce(lag(indicator_val) over (order by report_dt) - indicator_val, 0) as indicator_diff,
      	case
      		when (indicator_val is null or indicator_val = 0) then 0
      		when (indicator_val > 0 and lag(indicator_val) over (order by report_dt) = 0) then 100
      		when (lag(indicator_val) over (order by report_dt) <> 0) then coalesce(round((indicator_val / lag(indicator_val) over (order by report_dt) - 1) * 100), 0)
      		else 0
      	end indicator_diff_pct
      from
      	(
      		select
      			da.report_dt,
      			round(avg(kpi_cnt)) as indicator_val
      		from
      			dm.sfct_digital_analytics_daily da
      		where
      			(kpi_nm = :kpiParamName)
      			and (category_nm in (:recreations))
      		group by
      			da.report_dt
      		order by
      			da.report_dt
      ) as analytic_data;
      """;

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final TourismAvgStatsRowMapper rowMapper;

  public TourismAvgStatsQuery(@Qualifier(Persistence.GREENPLUM_STATS_JDBC_TEMPLATE_BEAN) NamedParameterJdbcTemplate jdbcTemplate,
                           TourismAvgStatsRowMapper rowMapper) {

    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
  }

  @Nonnull
  public List<TourismAvgStatsEntry> executeWith(@Nonnull TourismAvgStatsParams searchParams) {

    final var paramMapper = new MapSqlParameterSource("kpiParamName", searchParams.getStatsType().getKpiParamName())
        .addValue("recreations", searchParams.getRecreations())
        .addValue("tourismType", searchParams.getTourismType());

    try {
      return jdbcTemplate.query(SQL, paramMapper, rowMapper);
    } catch (Exception e) {
      throw new RuntimeException("Agrobit stats fetching error", e);
    }
  }
}
