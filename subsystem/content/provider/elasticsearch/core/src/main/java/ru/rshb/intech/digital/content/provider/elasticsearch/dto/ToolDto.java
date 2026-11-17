package ru.rshb.intech.digital.content.provider.elasticsearch.dto;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(
    indexName = "content--content-tools",
    createIndex = false
)
@Generated
public class ToolDto {

  @Id
  private String id;

  @Field(
      type = FieldType.Text,
      name = "id"
  )
  private String externalId;

  private Long views;
  private Long likes;
}
