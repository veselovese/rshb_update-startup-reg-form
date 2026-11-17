package ru.rshb.intech.digital.statistics.agrobit.tourism.repository.query;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Persistence;
import ru.rshb.intech.digital.statistics.agrobit.commons.ValueDiffStat;

import java.util.List;

@Component
public class StatsByTypeAndAllRecreationTypesQuery {

  private static final String SQL =
      """
      select
      	report_dt as "dt",
      	:statsType as cat,
      	:recreationType as sub,
      	val,
      	coalesce(val - lag(val) over (order by report_dt), 0) val_diff,
      	case
      		when (val is null or val = 0) then 0
      		when (val > 0 and lag(val) over (order by report_dt) = 0) then 100
      		when (lag(val) over (order by report_dt) <> 0) then coalesce(round((val / lag(val) over (order by report_dt) - 1) * 100), 0)
      		else 0
      	end val_diff_pct
      from (
      	select
      		report_dt,
      		round(avg(indicator_val)) as val
      	from digital_statistics.agrobit_tourism_avg_recr r
      	where r.stats_type = :statsType
      	group by report_dt
      	order by report_dt
      ) as anal;
      """;

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final ValueDiffStatRowMapper rowMapper;

  public StatsByTypeAndAllRecreationTypesQuery(
      @Qualifier(Persistence.DEFAULT_DB_JDBC_TEMPLATE_BEAN) NamedParameterJdbcTemplate jdbcTemplate,
      ValueDiffStatRowMapper rowMapper
  ) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
  }

  @Nonnull
  public List<ValueDiffStat> executeWith(@Nonnull String statsType, @Nonnull String subCatTitle) {

    final var paramMapper = new MapSqlParameterSource("statsType", statsType)
        .addValue("recreationType", subCatTitle);

    try {
      return jdbcTemplate.query(SQL, paramMapper, rowMapper);
    } catch (Exception e) {
      throw new RuntimeException("Agrobit cached stats fetching error", e);
    }
  }
}
