package ru.rshb.intech.digital.statistics.agrobit.tourism.repository.query;

import jakarta.annotation.Nonnull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.statistics.agrobit.commons.ValueDiffStat;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("tourismValueDiffStatRowMapper")
public class ValueDiffStatRowMapper implements RowMapper<ValueDiffStat> {

  @Override
  public ValueDiffStat mapRow(@Nonnull ResultSet rs, int rowNum) throws SQLException {
    final var value = new ValueDiffStat();
    value.setDate(rs.getDate("dt").toLocalDate());
    value.setCategory(rs.getString("cat"));
    value.setSubType(rs.getString("sub"));
    value.setValue(rs.getLong("val"));
    value.setValueDiff(rs.getLong("val_diff"));
    value.setValueDiffPct(rs.getLong("val_diff_pct"));
    return value;
  }
}
