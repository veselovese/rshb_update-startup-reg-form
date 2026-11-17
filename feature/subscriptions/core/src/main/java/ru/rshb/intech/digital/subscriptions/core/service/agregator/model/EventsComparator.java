package ru.rshb.intech.digital.subscriptions.core.service.agregator.model;

import java.util.Comparator;

public class EventsComparator implements Comparator<DataItem> {

  @Override
  public int compare(DataItem o1, DataItem o2) {
    return o1.getStartDate().compareTo(o2.getStartDate());
  }
}
