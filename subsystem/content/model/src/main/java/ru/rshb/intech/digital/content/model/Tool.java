package ru.rshb.intech.digital.content.model;

import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Generated
@EqualsAndHashCode(callSuper = true)
public class Tool extends Publication {

  public Tool() {
    super(ItemType.TOOL);
  }
}
