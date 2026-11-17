package ru.rshb.intech.digital.subscriptions.core.service.agregator.collector.mapper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.rshb.intech.digital.commons.utils.Locales;
import ru.rshb.intech.digital.content.model.Event;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.DataItem;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;

import java.time.LocalDate;
import java.time.format.TextStyle;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.FULL_DATE;

@Slf4j
@RequiredArgsConstructor
public class EventDataMapper implements DataMapper<Event> {

  @Nonnull
  @Override
  public DataItem dataItemForm(@Nonnull Event event) {

    final var dataItem = new DataItem();
    dataItem.setTitle(event.getTitle());
    dataItem.setSummary(event.getSummary());
    dataItem.setUrl(event.getUrl()); // TODO у событий абсолютный url
    dataItem.setCategoryType(CategoryType.EVENTS);
    dataItem.setCategories(event.getCategories());
    dataItem.setPlace(event.getCity());

    final var startDate = parseStartDate(event);
    if (startDate != null) {
      dataItem.setStartDate(startDate);
      dataItem.setStartDateFormatted(FULL_DATE.format(startDate));
    }

    final var endDate = parseEndDate(event);
    if (endDate != null) {
      dataItem.setEndDate(endDate);
      dataItem.setEndDateFormatted(FULL_DATE.format(endDate));
    }

    if (startDate != null && endDate != null) {
      dataItem.setFormattedTimeInterval(
          makeInterval(startDate, endDate)
      );
    }

    return dataItem;
  }

  @Nullable
  private LocalDate parseStartDate(@Nonnull Event event) {
    final var date = event.getStartDate();
    if (date == null) {
      log.warn("Event [{}] has no start date", event.getTitle());
      return null;
    }
    return date;
  }

  @Nullable
  private LocalDate parseEndDate(@Nonnull Event event) {
    final var date = event.getEndDate();
    if (date == null) {
      log.warn("Event [{}] has no end date", event.getTitle());
    }
    return date;
  }

  @Nonnull
  private String makeInterval(@Nonnull LocalDate startDate, @Nonnull LocalDate endDate) {

    final var isSameMonth = (startDate.getMonthValue() == endDate.getMonthValue());
    final var isDifferentDays = (startDate.getDayOfMonth() != endDate.getDayOfMonth());
    final var isSameYear = (startDate.getYear() == endDate.getYear());

    if (isSameYear && isSameMonth && isDifferentDays) {
      final var days = String.format("%s-%s", startDate.getDayOfMonth(), endDate.getDayOfMonth());
      final var localizedMonth = startDate.getMonth().getDisplayName(TextStyle.FULL, Locales.RU);
      final var year = startDate.getYear();
      return String.format("%s %s %s", days, localizedMonth, year);
    }

    return String.format("%s - %s", FULL_DATE.format(startDate), FULL_DATE.format(endDate));
  }
}
