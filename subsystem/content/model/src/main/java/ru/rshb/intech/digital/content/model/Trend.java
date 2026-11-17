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
public class Trend extends Publication {

  private LocalDate publishDate;
  private String slug;
  private String imageUrl;

  public Trend() {
    super(ItemType.TREND);
  }
}
