package ru.rshb.intech.digital.statistics.agrobit.commons;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.annotation.Nonnull;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class ValueDiffStat implements Comparable<ValueDiffStat> {

  private String category;
  private String subType;
  private LocalDate date;
  private Long value;
  private Long valueDiff;
  private Long valueDiffPct;

  @Override
  public int compareTo(@Nonnull ValueDiffStat other) {

    final var thisDate = date;
    final var otherDate = other.getDate();

    if (thisDate == null && otherDate == null) {
      return 0;
    }

    if (thisDate == null) {
      return -1;
    }

    if (otherDate == null) {
      return 1;
    }

    if (thisDate.isEqual(otherDate)) {
      return 0;
    }

    if (thisDate.isBefore(otherDate)) {
      return 1;
    }

    return -1;
  }
}
