package ru.rshb.intech.digital.subscriptions.core.service.agregator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataItem {

  private CategoryType categoryType;
  private List<String> categories;
  private String imageUrl;
  private String url;
  private String title;
  private String subTitle;
  private String summary;
  private String place;

  private LocalDate publicationDate;
  private String publicationDateFormatted;

  private LocalDate startDate;
  private String startDateFormatted;

  private LocalDate endDate;
  private String endDateFormatted;

  private String formattedTimeInterval;

}
