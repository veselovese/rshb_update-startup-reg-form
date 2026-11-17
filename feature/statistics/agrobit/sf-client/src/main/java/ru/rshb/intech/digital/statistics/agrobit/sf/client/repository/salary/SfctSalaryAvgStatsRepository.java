package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.PositionCategory;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.SfctSalaryCategoryStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.SfctSalaryPositionStatsEntry;

import java.util.List;

public interface SfctSalaryAvgStatsRepository {

  /**
   * Возвращает год самых ранних данных статистики.
   *
   * @return Год.
   */
  int findDataMinYear();

  /**
   * Возвращает данные статистики о средней зарплате по профессии
   * за период с начала заданного года по текущее время.
   *
   * @param startYear Год начала интересующего периода.
   * @param category Категория.
   * @param position Профессия.
   * @return Данные статистики по месяцам.
   */
  @Nonnull
  List<SfctSalaryPositionStatsEntry> findAvgSalaryByPosition(int startYear, @Nonnull String category, @Nonnull String position);

  /**
   * Возвращает данные статистики о средней зарплате по заданным профессиям
   * за период с начала заданного года по текущее время.
   *
   * @param startYear Год начала интересующего периода.
   * @param category Категория.
   * @param positions Профессии.
   * @return Данные статистики по месяцам.
   */
  @Nonnull
  List<SfctSalaryPositionStatsEntry> findAvgSalaryByPositions(int startYear, @Nonnull String category, @Nonnull List<String> positions);

  /**
   * Возвращает данные статистики о средней зарплате по всем профессиям
   * за период с начала заданного года по текущее время.
   *
   * @param startYear Год начала интересующего периода.
   * @return Данные статистики по месяцам.
   */
  @Nonnull
  List<SfctSalaryPositionStatsEntry> findAvgSalaryByAllPositions(int startYear);


  /**
   * Возвращает данные статистики о зарплате по категории месяц к месяцу.
   *
   * @param positionCategory Категория профессии.
   * @return Данные статистики месяц к месяцу.
   */
  @Nonnull
  List<SfctSalaryCategoryStatsEntry> findAvgSalaryByCategoryMonthOverMonth(@Nonnull PositionCategory positionCategory);

  /**
   * Возвращает данные статистики о средней зарплате по всем категориям
   * месяц к месяцу.
   *
   * @return Данные статистики месяц к месяцу.
   */
  @Nonnull
  List<SfctSalaryCategoryStatsEntry> findAvgSalaryByAllPositionsMonthOverMonth();

}
