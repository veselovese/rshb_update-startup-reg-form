package ru.rshb.intech.digital.feature.internship.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;

import java.util.List;

@Schema(name = "Справочники для формы обратной связи о прохождении стажировки")
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public record InternshipFeedbackLookupsDto (List<LookupItem> recommendationSource) {
}
