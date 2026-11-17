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

@Document(
    indexName = "content--content-podcast",
    createIndex = false
)
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class PodcastDto {

  @Id
  private String id;

  private Long views;
  private Long likes;

  @Field(
      type = FieldType.Text,
      name = "id"
  )
  private String externalId;

  private String title;
  private String label;
  private String slug;
  private String description;

  @Field(
      type = FieldType.Date,
      name = "publishDate"
  )
  private LocalDate publicationDate;
  private String channel;
}
