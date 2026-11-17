package ru.rshb.intech.digital.content.model;

import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Generated
@EqualsAndHashCode(callSuper = true)
public class Podcast extends Publication {

  private LocalDate publishDate;
  private String title;
  private String label;
  private String slug;
  private String description;
  private String channel;


  public Podcast() {
    super(ItemType.PODCAST);
  }
}
