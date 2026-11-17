package ru.rshb.intech.digital.vacancies.hh.dto.vacancy.list;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountersDto {

  private Long views;

  private Long responses;

  @JsonProperty("unread_responses")
  private Long unreadResponses;

  @JsonProperty("resumes_in_progress")
  private Long resumesInProgress;

  private Long invitations;

  @JsonProperty("invitations_and_responses")
  private Long invitationsAndResponses;
}
