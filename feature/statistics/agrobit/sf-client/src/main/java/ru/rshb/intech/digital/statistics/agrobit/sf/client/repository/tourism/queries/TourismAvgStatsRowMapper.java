package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries;

import jakarta.annotation.Nonnull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism.TourismAvgStatsEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TourismAvgStatsRowMapper implements RowMapper<TourismAvgStatsEntry> {

  @Override
  public TourismAvgStatsEntry mapRow(@Nonnull ResultSet rs, int rowNum) throws SQLException {
    final var record = new TourismAvgStatsEntry();
    record.setReportDate(rs.getDate("report_dt").toLocalDate());
    record.setTourismType(rs.getString("tourism_type"));
    record.setIndicatorValue(rs.getLong("indicator_val"));
    record.setIndicatorDiff(rs.getLong("indicator_diff"));
    record.setIndicatorDiffPct(rs.getLong("indicator_diff_pct"));
    return record;
  }
}
