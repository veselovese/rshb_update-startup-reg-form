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
    indexName = "content--content-lecture",
    createIndex = false
)
@Generated
public class LectureDto {

  @Id
  private String id;

  @Field(
      type = FieldType.Text,
      name = "id"
  )
  private String externalId;

  private Long views;

  private Long likes;

  private String title;

  @Field(
      type = FieldType.Auto,
      name = "label"
  )
  private List<String> labels;

  private String summary;

  @Field(
      type = FieldType.Text,
      name = "slug"
  )
  private String slug;

  @Field(
      type = FieldType.Text,
      name = "image"
  )
  private String image;
}
