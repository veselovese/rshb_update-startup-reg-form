package ru.rshb.intech.digital.content.provider.elasticsearch.dto;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.ValueConverter;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.converter.NullableLocalDateConverter;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(
    indexName = "content--content-events",
    createIndex = false
)
@Generated
public class EventDto {

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
  private String summary;

  @Field(
      name = "eventDateStart",
      type = FieldType.Date,
      format = DateFormat.date
  )
  @ValueConverter(NullableLocalDateConverter.class)
  private LocalDate startDate;

  @Field(
      name = "eventDateEnd",
      type = FieldType.Date,
      format = DateFormat.date
  )
  @ValueConverter(NullableLocalDateConverter.class)
  private LocalDate endDate;
  private String city;
  private String slug;

  private String url;

  @Field(
      type = FieldType.Text,
      name = "category"
  )
  private List<String> categories;
}
