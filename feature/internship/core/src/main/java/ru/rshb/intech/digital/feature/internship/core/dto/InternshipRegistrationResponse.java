package ru.rshb.intech.digital.feature.internship.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;
import lombok.Getter;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;
import ru.rshb.intech.digtial.commons.dto.ResultStatus;


@Schema(name = "Результат приема заявки на стажировку")
@Getter
@Generated
public class InternshipRegistrationResponse extends OperationResultResponse {

  @Schema(description = "Email пользователя, оставившего заявку на стажировку")
  private final String userEmail;

  public InternshipRegistrationResponse(String userEmail) {
    super(ResultStatus.SUCCESS, null);

    this.userEmail = userEmail;
  }
}
