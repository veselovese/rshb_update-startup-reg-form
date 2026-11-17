package ru.rshb.intech.digital.content.model;


import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Generated
@EqualsAndHashCode(callSuper = true)
public class Lecture extends Publication {

  private String title;
  private String summary;
  private String slug;
  private List<String> labels;
  private String image;


  public Lecture() {
    super(ItemType.LECTURE);
  }
}
