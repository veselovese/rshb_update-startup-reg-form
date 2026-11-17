package ru.rshb.intech.digital.references.cities.core.service;

import ru.rshb.intech.digital.references.cities.entity.City;

import jakarta.annotation.Nonnull;
import java.util.Comparator;

public class CityNameComparator implements Comparator<City> {
  private final String template;

  public CityNameComparator(@Nonnull String template) {
    this.template = template.toLowerCase();
  }

  @Override
  public int compare(City c1, City c2) {

    final var t1 = c1.getTitle().toLowerCase();
    final var t2 = c2.getTitle().toLowerCase();

    final int i2 = t2.indexOf(template);
    final int i1 = t1.indexOf(template);

    if (i1 > i2) {
      return 1;
    }

    if (i1 < i2) {
      return -1;
    }

    return Integer.compare(t1.length(), t2.length());
  }
}
