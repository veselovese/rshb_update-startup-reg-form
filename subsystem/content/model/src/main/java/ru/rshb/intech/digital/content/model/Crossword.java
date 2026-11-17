package ru.rshb.intech.digital.content.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Generated
@EqualsAndHashCode(callSuper = true)
public class Crossword extends DistinctItem {

  private String title;
  private String description;
  private List<String> categories;
  private List<Question> questions;

  @Getter
  @Setter
  @Generated
  @EqualsAndHashCode
  public static class Question {
    private String id;
    private String title;
    private long[] start;
    private long count;
    private String answer;
    private Direction direction;

    @Getter
    @Generated
    @RequiredArgsConstructor
    public enum Direction {

      ROW("row"),
      COLUMN("column");

      private final String key;

      @JsonValue
      public String getKey() {
        return key;
      }
    }
  }
}
