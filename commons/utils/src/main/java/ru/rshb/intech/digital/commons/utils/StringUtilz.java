package ru.rshb.intech.digital.commons.utils;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.split;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtilz {

  /**
   * Преобразует строку "a,b,c" в список {"a", "b", "c"}.
   * Если передана пустая строка или <code>null</code> - возвращает пустой список.
   *
   * @param str Строка со значениями, отделенными запятыми.
   * @return Список подстрок.
   */
  @Nonnull
  public static List<String> splitOrEmpty(@Nullable String str) {
    if (isBlank(str)) {
      return Collections.emptyList();
    }
    return new ArrayList<>(
        Arrays.asList(split(str, ","))
    );
  }
}
