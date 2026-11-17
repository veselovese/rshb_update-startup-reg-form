package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Persistence;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism.TourismStatsEntry;

import java.util.List;

@Component
public class TourismStatsQuery {

  private static final String SQL =
      """
      select
        report_dt,
        :tourismType as "tourism_type",
        :recreationType as "recreation_type",
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
              and (category_nm in (:tourismCats))
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
  private final TourismStatsRowMapper rowMapper;

  public TourismStatsQuery(@Qualifier(Persistence.GREENPLUM_STATS_JDBC_TEMPLATE_BEAN) NamedParameterJdbcTemplate jdbcTemplate,
                           TourismStatsRowMapper rowMapper) {

    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
  }


  @Nonnull
  public List<TourismStatsEntry> executeWith(@Nonnull TourismStatsByTypeParams searchParams) {

    final var paramMapper = new MapSqlParameterSource("kpiParamName", searchParams.getStatsType().getKpiParamName())
        .addValue("tourismType", searchParams.getTourismType())
        .addValue("recreationType", searchParams.getRecreationType())
        .addValue("startDate", searchParams.getPeriodStart())
        .addValue("endDate", searchParams.getPeriodEnd())
        .addValue("tourismCats", searchParams.getCategories());

    try {
      return jdbcTemplate.query(SQL, paramMapper, rowMapper);
    } catch (Exception e) {
      throw new RuntimeException("Agrobit stats fetching error", e);
    }
  }
}
