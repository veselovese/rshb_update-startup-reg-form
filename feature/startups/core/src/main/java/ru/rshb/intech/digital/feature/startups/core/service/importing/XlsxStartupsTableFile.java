package ru.rshb.intech.digital.feature.startups.core.service.importing;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.rshb.intech.digital.feature.startups.entity.StartupRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Читает данные о стартапах/компаниях из файла в формате XLSX.
 */
public class XlsxStartupsTableFile implements StartupReader {

  private static final int STARTUPS_SHEET_INDEX = 0;

  private final XSSFSheet sheet;
  private final int rowStartIndex;

  private XlsxStartupsTableFile(@Nonnull InputStream inputStream, int rowStartIndex) throws IOException {
    final var wb = new XSSFWorkbook(inputStream);
    this.sheet = wb.getSheetAt(STARTUPS_SHEET_INDEX);
    this.rowStartIndex = rowStartIndex;
  }

  /**
   * Создает новый инстанс ридера XLSX файлов.
   *
   * @param inputStream Входной поток.
   * @param rowStartIndex Номер строки таблицы, с которой начинать чтение.
   * @return Инстанс ридера.
   * @throws IOException Ошибка при открытии файла.
   */
  @Nonnull
  public static StartupReader open(@Nonnull InputStream inputStream, int rowStartIndex) throws IOException {
    return new XlsxStartupsTableFile(inputStream, rowStartIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public Iterator<StartupRecord> iterator() {
    return new StartupIterator(sheet, rowStartIndex);
  }

  @AllArgsConstructor
  private static class StartupIterator implements Iterator<StartupRecord> {

    private static final int TITLE_CELL_INDEX = 0;

    private final XSSFSheet sheet;
    private int rowIndex;
    private final DataFormatter formatter = new DataFormatter();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {

      final var row = sheet.getRow(rowIndex);
      if (row == null) {
        return false;
      }

      return isCellNotEmpty(
          row.getCell(TITLE_CELL_INDEX)
      );
    }

    private boolean isCellNotEmpty(@Nonnull XSSFCell cell) {
      final var cellValue = asString(cell);
      return StringUtils.isNotBlank(cellValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StartupRecord next() {

      final var row = sheet.getRow(rowIndex);
      final var startup = new StartupRecord();

      int ci = 0;
      startup.setTitle(
          asString(row.getCell(ci++))
      );
      startup.setSummary(
          asString(row.getCell(ci++))
      );
      startup.setDescription(
          asString(row.getCell(ci++))
      );
      startup.setSiteLink(
          asString(row.getCell(ci++))
      );
      startup.setWorkers(
          asString(row.getCell(ci++))
      );
      startup.setYear(
          asString(row.getCell(ci++))
      );
      startup.setLogo(
          asString(row.getCell(ci++))
      );
      startup.setRound(
          asString(row.getCell(ci++))
      );
      startup.setIntegrations(
          asString(row.getCell(ci++))
      );
      startup.setEtap(
          asString(row.getCell(ci++))
      );
      startup.setCategory(
          asString(row.getCell(ci++))
      );
      startup.setCountry(
          asString(row.getCell(ci++))
      );
      startup.setArchieve(
          asString(row.getCell(ci++))
      );
      startup.setDistrict(
          asString(row.getCell(ci++))
      );
      startup.setRegion(
          asString(row.getCell(ci++))
      );
      startup.setMoney(
          asString(row.getCell(ci++))
      );
      startup.setMoneyType(
          asString(row.getCell(ci++))
      );
      startup.setHidden(
          asString(row.getCell(ci++))
      );
      startup.setFile(
          asString(row.getCell(ci))
      );

      rowIndex++;

      return startup;
    }

    private String asString(@Nonnull XSSFCell cell) {
      return formatter.formatCellValue(cell);
    }
  }
}
