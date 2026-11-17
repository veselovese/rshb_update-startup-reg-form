package ru.rshb.intech.digital.statistics.agrobit.tourism.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Getter
@RequiredArgsConstructor
public class TourismStatsFilter {

  private final String categoryId;
  private final List<String> recreationTypeIds = new ArrayList<>();

  public void setRecreationTypeIds(@Nullable String recreationTypeIds) {

    final var ids = isNotBlank(recreationTypeIds)
        ? Arrays.asList(recreationTypeIds.split(","))
        : List.<String>of();

    this.recreationTypeIds.clear();
    this.recreationTypeIds.addAll(ids);
  }

}
