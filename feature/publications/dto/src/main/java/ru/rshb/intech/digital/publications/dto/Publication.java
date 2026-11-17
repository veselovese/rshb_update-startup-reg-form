package ru.rshb.intech.digital.publications.dto;

import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rshb.intech.digtial.commons.dto.DistinctItem;

@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Publication extends DistinctItem {

  private Long views;
  private Long likes;
}
