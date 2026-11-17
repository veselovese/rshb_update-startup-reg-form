package ru.rshb.intech.digital.content.provider.elasticsearch.dto;

import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document(
    indexName = "content--content-infographic",
    createIndex = false
)
@Generated
public class InfographicDto {

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
      type = FieldType.Auto,
      name = "label"
  )
  private List<String> categories;

  private String title;

  private String summary;
  private String description;

  @Field(
      type = FieldType.Date,
      name = "publishDate"
  )
  private LocalDate publicationDate;

  @Field(
      type = FieldType.Auto,
      name = "modal_image"
  )
  private String modalImage;

  @Field(
      type = FieldType.Auto,
      name = "cover_image"
  )
  private String coverImage;

  private String image;

  @Field(
      type = FieldType.Auto,
      name = "download_image"
  )
  private String downloadImage;

  @Field(
      type = FieldType.Text,
      name = "slug"
  )
  private String slug;

  @Field(
      type = FieldType.Boolean,
      name = "is_light_theme"
  )
  private Boolean isLightTheme;

  @Field(
      type = FieldType.Text,
      name = "diagramma"
  )
  private String diagramma;
}
