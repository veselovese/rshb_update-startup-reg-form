package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.queries;

import jakarta.annotation.Nonnull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.SfctSalaryPositionStatsEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SalaryPositionStatsRowMapper implements RowMapper<SfctSalaryPositionStatsEntry> {

  @Override
  public SfctSalaryPositionStatsEntry mapRow(@Nonnull ResultSet rs, int rowNum) throws SQLException {
    final var record = new SfctSalaryPositionStatsEntry();
    record.setReportDate(rs.getDate("report_dt").toLocalDate());
    record.setCategory(rs.getString("cat_name"));
    record.setPosition(rs.getString("pos_name"));
    record.setIndicatorValue(rs.getLong("indicator_val"));
    record.setIndicatorDiff(rs.getLong("indicator_diff"));
    record.setIndicatorDiffPct(rs.getLong("indicator_diff_pct"));
    return record;
  }
}

