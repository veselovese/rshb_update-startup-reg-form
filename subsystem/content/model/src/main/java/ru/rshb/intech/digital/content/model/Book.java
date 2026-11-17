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
public class Book extends Publication {

  private String title;
  private LocalDate publishDate;
  private List<String> labels;
  private String slug;
  private String image;

  public Book() {
    super(ItemType.BOOK);
  }
}
