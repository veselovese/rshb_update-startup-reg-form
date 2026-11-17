package ru.rshb.intech.digital.content.provider.elasticsearch.dto;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Document(
    indexName = "content--content-crossword",
    createIndex = false
)
@Generated
public class CrosswordDto {

  @Id
  private String id;

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
      type = FieldType.Text,
      name = "categories"
  )
  private List<String> categories;

  @Field(
      type = FieldType.Nested,
      name = "questions",
      includeInParent = true
  )
  private List<CrosswordQuestionDto> questions;
}
