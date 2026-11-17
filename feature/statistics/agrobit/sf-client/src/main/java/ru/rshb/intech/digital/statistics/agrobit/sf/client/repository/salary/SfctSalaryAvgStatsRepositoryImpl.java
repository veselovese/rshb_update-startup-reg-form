package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.PositionCategory;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.SfctSalaryCategoryStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.SfctSalaryPositionStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.queries.SalaryAvgByAllPosMonthOverMonthQuery;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.queries.SalaryAvgByAllPositionsQuery;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.queries.SalaryAvgByCatMonthOverMonthQuery;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.queries.SalaryAvgByPositionQuery;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.queries.SalaryAvgByPositionsQuery;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.queries.SalaryEarliestStatDateQuery;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SfctSalaryAvgStatsRepositoryImpl implements SfctSalaryAvgStatsRepository {

  private final SalaryEarliestStatDateQuery salaryEarliestStatDateQuery;
  private final SalaryAvgByPositionQuery salaryAvgByPositionQuery;
  private final SalaryAvgByPositionsQuery salaryAvgByPositionsQuery;
  private final SalaryAvgByAllPositionsQuery salaryAvgByAllPositionsQuery;
  private final SalaryAvgByCatMonthOverMonthQuery salaryAvgByCatMonthOverMonthQuery;
  private final SalaryAvgByAllPosMonthOverMonthQuery salaryAvgByAllPosMonthOverMonthQuery;


  /**
   * {@inheritDoc}
   */
  @Override
  public int findDataMinYear() {
    return salaryEarliestStatDateQuery.execute();
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public List<SfctSalaryPositionStatsEntry> findAvgSalaryByPosition(int startYear,
                                                                    @Nonnull String category,
                                                                    @Nonnull String position) {
    return salaryAvgByPositionQuery.executeWith(
        SalaryAvgByPositionQuery.Params.builder()
            .startYear(startYear)
            .category(category)
            .position(position)
            .build()
    );
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public List<SfctSalaryPositionStatsEntry> findAvgSalaryByPositions(int startYear,
                                                                     @Nonnull String category,
                                                                     @Nonnull List<String> positions) {
    return salaryAvgByPositionsQuery.executeWith(
        SalaryAvgByPositionsQuery.Params.builder()
            .startYear(startYear)
            .category(category)
            .positions(positions)
            .build()
    );
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public List<SfctSalaryPositionStatsEntry> findAvgSalaryByAllPositions(int startYear) {
    return salaryAvgByAllPositionsQuery.execute(startYear);
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public List<SfctSalaryCategoryStatsEntry> findAvgSalaryByCategoryMonthOverMonth(@Nonnull PositionCategory positionCategory) {
    return salaryAvgByCatMonthOverMonthQuery.executeWith(positionCategory);
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public List<SfctSalaryCategoryStatsEntry> findAvgSalaryByAllPositionsMonthOverMonth() {
    return salaryAvgByAllPosMonthOverMonthQuery.execute();
  }
}
