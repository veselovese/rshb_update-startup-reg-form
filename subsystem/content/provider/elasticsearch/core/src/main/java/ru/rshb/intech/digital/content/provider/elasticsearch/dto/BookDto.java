package ru.rshb.intech.digital.content.provider.elasticsearch.dto;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.ValueConverter;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.converter.NullableLocalDateConverter;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(
    indexName = "content--content-books",
    createIndex = false
)
@Generated
public class BookDto {

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

  @Field(
      type = FieldType.Date,
      name = "publishDate"
  )
  @ValueConverter(NullableLocalDateConverter.class)
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
