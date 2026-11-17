package ru.rshb.intech.digital.content.model;

import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Generated
@EqualsAndHashCode(callSuper = true)
public class Event extends Publication {

  private List<String> categories;
  private String title;
  private String summary;
  private String url;
  private String slug;
  private LocalDate startDate;
  private LocalDate endDate;
  private String city;

  public Event() {
    super(ItemType.EVENT);
  }
}
