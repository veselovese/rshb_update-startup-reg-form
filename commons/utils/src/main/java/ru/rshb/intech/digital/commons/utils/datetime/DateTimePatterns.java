package ru.rshb.intech.digital.commons.utils.datetime;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimePatterns {

  public static final String SIMPLE_DATE = "dd.MM.yyyy";
  public static final String SIMPLE_DATE_TIME = "dd.MM.yyyy HH:mm:ss";
  public static final String FULL_DATE = "dd MMMM yyyy";
  public static final String ISO_EXTENDED_DATE = "yyyy-MM-dd";
}
