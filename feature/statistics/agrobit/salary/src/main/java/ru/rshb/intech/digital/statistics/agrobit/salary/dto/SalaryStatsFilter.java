package ru.rshb.intech.digital.statistics.agrobit.salary.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Getter
@RequiredArgsConstructor
public class SalaryStatsFilter {

  private final String categoryId;
  private final List<String> positionIds = new ArrayList<>();


  public void setPositionIds(@Nonnull String positionIds) {

    final var posIds = isNotBlank(positionIds)
        ? Arrays.asList(positionIds.split(","))
        : List.<String>of();

    this.positionIds.clear();
    this.positionIds.addAll(new HashSet<>(posIds));

  }
}
