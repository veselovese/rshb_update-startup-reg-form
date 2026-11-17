package ru.rshb.intech.digital.content.provider.elasticsearch.dto;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Data
@Document(
    indexName = "content--content-trands",
    createIndex = false
)
@Generated
public class TrendDto {

  @Id
  private String id;

  @Field(
      type = FieldType.Text,
      name = "id"
  )
  private String externalId;

  private Long views;
  private Long likes;

  @Field(
      type = FieldType.Date,
      name = "publishDate"
  )
  private LocalDate publicationDate;

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
