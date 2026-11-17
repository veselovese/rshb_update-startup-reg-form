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
public class Article extends Publication {

  private String title;
  private String summary;
  private String url;
  private String slug;
  private List<String> labels;
  private LocalDate publishDate;
  private String type;
  private String image;

  public Article() {
    super(ItemType.ARTICLE);
  }
}
