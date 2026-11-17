package ru.rshb.intech.digital.content.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Generated
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class Publication extends DistinctItem {

  private Long views;
  private Long likes;

  @JsonIgnore
  private final ItemType itemType;
}
