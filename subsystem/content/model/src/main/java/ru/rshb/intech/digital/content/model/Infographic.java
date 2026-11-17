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
public class Infographic extends Publication {

  private List<String> labels;
  private String title;
  private String summary;
  private String description;
  private LocalDate publishDate;
  private String modalImage;
  private String coverImage;
  private String image;
  private String downloadImage;
  private String slug;
  private Boolean isLightTheme;
  private String diagramma;

  public Infographic() {
    super(ItemType.INFOGRAPHIC);
  }
}
