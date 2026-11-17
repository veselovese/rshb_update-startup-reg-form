package ru.rshb.intech.digital.puzzles.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import ru.rshb.intech.digtial.commons.dto.DistinctItem;

import java.util.List;

@Getter
@Setter
@Generated
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrosswordContentDto extends DistinctItem {

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  private List<String> categories;

  private List<CrosswordQuestionDto> questions;
}
