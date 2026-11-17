package ru.rshb.intech.digital.content.model;

import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Generated
@EqualsAndHashCode(callSuper = true)
public class Technology extends Publication {

  private String title;
  private String slug;

  public Technology() {
    super(ItemType.TECHNOLOGY);
  }
}
