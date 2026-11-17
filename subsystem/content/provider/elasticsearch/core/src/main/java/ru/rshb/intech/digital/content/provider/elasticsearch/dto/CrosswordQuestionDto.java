package ru.rshb.intech.digital.content.provider.elasticsearch.dto;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.ValueConverter;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.converter.WordDirectionConverter;

@Data
@Generated
public class CrosswordQuestionDto {

  @Field(
      type = FieldType.Text,
      name = "id"
  )
  private String externalId;

  @Field(
      type = FieldType.Text,
      name = "title"
  )
  private String title;

  @Field(
      type = FieldType.Long,
      name = "start"
  )
  private long[] start;

  @Field(
      type = FieldType.Text,
      name = "direction"
  )
  @ValueConverter(WordDirectionConverter.class)
  private WordDirection direction;

  @Field(
      type = FieldType.Text,
      name = "answer"
  )
  private String answer;
}
