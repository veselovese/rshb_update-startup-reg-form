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
public class Innovation extends Publication {

  private String title;
  private String summary;
  private String description;
  private List<String> categories;
  private Boolean closed;
  private String slug;
  private String image;
  private String url;


  public Innovation() {
    super(ItemType.INNOVATION);
  }
}

