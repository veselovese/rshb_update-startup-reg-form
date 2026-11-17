package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.queries;

import jakarta.annotation.Nonnull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.SfctSalaryCategoryStatsEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SalaryAvgCategoryStatsRowMapper implements RowMapper<SfctSalaryCategoryStatsEntry> {

  @Override
  public SfctSalaryCategoryStatsEntry mapRow(@Nonnull ResultSet rs, int rowNum) throws SQLException {
    final var record = new SfctSalaryCategoryStatsEntry();
    record.setReportDate(rs.getDate("report_dt").toLocalDate());
    record.setIndicatorValue(rs.getLong("indicator_val"));
    record.setIndicatorDiff(rs.getLong("indicator_diff"));
    record.setCategory(rs.getString("category_name"));
    return record;
  }
}
