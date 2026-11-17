package ru.rshb.intech.digital.subscriptions.core.service.agregator.model;

import java.util.Comparator;

public class PodcastComparator implements Comparator<DataItem> {

  @Override
  public int compare(DataItem o1, DataItem o2) {
    return o1.getPublicationDate().compareTo(o2.getPublicationDate());
  }
}
