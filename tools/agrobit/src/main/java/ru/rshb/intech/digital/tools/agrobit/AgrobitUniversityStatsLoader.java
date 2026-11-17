package ru.rshb.intech.digital.tools.agrobit;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.entity.EduType;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.entity.Faculty;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.entity.University;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.entity.YearStatsDataItem;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.repository.AgrobitStatsFacultyRepository;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.repository.AgrobitStatsUniversityRepository;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.repository.AgrobitYearStatsRepository;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Slf4j
@RequiredArgsConstructor
public class AgrobitUniversityStatsLoader {

  private final AgrobitStatsUniversityRepository universityRepository;
  private final AgrobitStatsFacultyRepository facultyRepository;
  private final AgrobitYearStatsRepository statsRepository;

  @Transactional
  public void loadData(@Nonnull String dataFile) {
    try {
      deleteOldData();
      loadBudgetData(dataFile);
      loadCommercialDate(dataFile);
    } catch (Exception e) {
      throw new RuntimeException("Agrobit University stats loading error", e);
    }
  }

  @Transactional
  public void loadCommercialDate(@Nonnull String dataFile) {
    final var newData = readNewDataFromFile(dataFile, "платно линейный", 3, 197);
    saveData(newData, EduType.COMMERCIAL);
  }

  @Transactional
  public void loadBudgetData(@Nonnull String dataFile) {

    final var newData = readNewDataFromFile(dataFile, "бюджет линейный обн", 3, 200);
    saveData(newData, EduType.BUDGET);
  }

  @Transactional
  public void deleteOldData() {
    try {
      universityRepository.deleteAll();
      log.info("Old Agrobit University stats data deleted.");
    } catch (Exception e) {
      throw new RuntimeException("Agrobit University stats old data delete error", e);
    }
  }

  @Nonnull
  private List<UniversityRow> readNewDataFromFile(@Nonnull String dataFile,
                                                  @Nonnull String sheetName,
                                                  int startRowIndex,
                                                  int endRowIndex) {


    final var universityRows = new ArrayList<UniversityRow>();

    try (final var fis = new FileInputStream(dataFile);
         final var workbook = new XSSFWorkbook(fis)) {

      final var sheet = workbook.getSheet(sheetName);

      UniversityRow universityRow = null;
      for (final var row : sheet) {

        final var rowIndex = row.getRowNum();
        log.info("Row Num: {}", rowIndex);

        if (rowIndex < startRowIndex) {
          continue;
        }

        if (rowIndex == endRowIndex) {
          break;
        }

        final var universityTitle = getCellValue(row.getCell(1));

        if (isNotBlank(universityTitle)) {
          universityRow = new UniversityRow(universityTitle.trim());
          universityRows.add(universityRow);
          continue;
        }

        final var facultyTitle = getCellValue(row.getCell(2));
        if (isNotBlank(facultyTitle)) {

          final var faculty = new FacultyRow(facultyTitle.trim());
          universityRow.getFacultyRows().add(faculty);

          final var facStats = faculty.getStatValuesByYears();

          facStats.put(2020L, getLongValue(getCellValue(row.getCell(3))));
          facStats.put(2021L, getLongValue(getCellValue(row.getCell(4))));
          facStats.put(2022L, getLongValue(getCellValue(row.getCell(5))));
          facStats.put(2023L, getLongValue(getCellValue(row.getCell(6))));

          continue;
        }
      }

      return universityRows;
    } catch (Exception e) {
      throw new RuntimeException("Data file reading error", e);
    }
  }

  @Nullable
  private Long getLongValue(@Nullable String strVal) {
    if (isBlank(strVal)) {
      return null;
    }
    return Long.parseLong(strVal);
  }

  @Nullable
  private String getCellValue(@Nonnull Cell cell) {
    return switch (cell.getCellType()) {
      case STRING -> cell.getStringCellValue();
      case NUMERIC -> String.valueOf(Math.round(cell.getNumericCellValue()));
      case BLANK -> null;
      default -> throw new RuntimeException("Unknown cell type: " + cell.getCellType());
    };
  }

  private void saveData(@Nonnull List<UniversityRow> universitiesData, @Nonnull EduType eduType) {

    for (final var universityData : universitiesData) {

      final var university = universityRepository.findByNameIgnoreCase(universityData.getUniversityName())
          .orElseGet(() -> new University());

      university.setName(universityData.getUniversityName());
      university.setShortName(universityData.getUniversityName()); // TODO search in refs

      final var savedUniversity = universityRepository.saveAndFlush(university);

      final var facultiesData = universityData.getFacultyRows();

      for (final var facultyData : facultiesData) {

        final var faculty = new Faculty();
        faculty.setName(facultyData.getFacultyName());
        faculty.setUniversity(savedUniversity);
        faculty.setType(eduType);

        final var savedFaculty = facultyRepository.saveAndFlush(faculty);

        final var statsByYear = new ArrayList<YearStatsDataItem>();
        for (final var statsData: facultyData.getStatValuesByYears().entrySet()) {
          final var statsItem = new YearStatsDataItem();
          statsItem.setFaculty(savedFaculty);
          statsItem.setYear(statsData.getKey());
          statsItem.setValue(statsData.getValue());
          statsByYear.add(statsItem);
        }

        statsRepository.saveAllAndFlush(statsByYear);
      }
    }
  }

  @Data
  public static class UniversityRow {

    private String universityName;
    private List<FacultyRow> facultyRows = new ArrayList<>();

    public UniversityRow(@Nonnull String name) {
      this.universityName = name;
    }
  }

  @Data
  public static class FacultyRow {

    private String facultyName;
    private Map<Long, Long> statValuesByYears = new HashMap<>();

    public FacultyRow(@Nonnull String name) {
      this.facultyName = name;
    }
  }
}
