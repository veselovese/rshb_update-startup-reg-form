package ru.rshb.intech.digital.content.provider.elasticsearch.dto;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;


@Data
@Document(
    indexName = "content--content-articles",
    createIndex = false
)
@Generated
public class ArticleDto {

  @Id
  private String id;

  @Field(
      type = FieldType.Text,
      name = "id"
  )
  private String externalId;

  private Long views;
  private Long likes;

  private String url;

  private String title;
  private String summary;

  @Field(
      type = FieldType.Auto,
      name = "label"
  )
  private List<String> categories;

  @Field(
      type = FieldType.Date,
      name = "publishDate"
  )
  private LocalDate publicationDate;

  @Field(
      type = FieldType.Text,
      name = "type"
  )
  private String publicationType;

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

