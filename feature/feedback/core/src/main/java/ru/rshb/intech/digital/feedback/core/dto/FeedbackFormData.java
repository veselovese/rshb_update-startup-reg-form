package ru.rshb.intech.digital.feedback.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Generated;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;

@Schema(name = "Данные формы обратной связи")
@Generated
public record FeedbackFormData(

    @Schema(description = "ФИО отправителя")
    String reporterName,

    @Schema(description = "Адрес электронной почты отправителя.")
    @NotBlank(message = "{common.error.validation.email.missing}")
    @Email(message = "{common.error.validation.email.format}")
    String email,

    @Schema(description = "Контактный номер телефона отправителя.")
    @Phone(message = "{common.error.validation.phone.format}")
    String phone,

    @Schema(description = "Текст обращения.")
    @NotBlank(message = "{feedback.report.error.validation.feedback.missing}")
    String feedback,

    @Schema(description = "Файл с дополнительной информацией. Не болеее 10 МБ.")
    MultipartFile attachment
) {

}
