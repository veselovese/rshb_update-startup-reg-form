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
    indexName = "content--content-innovation",
    createIndex = false
)
@Generated
public class InnovationDto {

  @Id
  private String id;

  @Field(
      type = FieldType.Text,
      name = "id"
  )
  private String externalId;

  private Long views;
  private Long likes;
  private Long responses;

  private String title;
  private String summary;
  private String description;

  @Field(
      type = FieldType.Text,
      name = "category"
  )
  private List<String> categories;

  private Boolean closed;
  private String slug;
  private String image;
  private String url;
}
