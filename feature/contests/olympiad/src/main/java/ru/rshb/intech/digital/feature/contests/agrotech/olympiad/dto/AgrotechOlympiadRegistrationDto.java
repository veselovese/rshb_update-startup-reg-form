package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Generated;
import ru.rshb.intech.digital.commons.validation.phone.Phone;
import ru.rshb.intech.digital.commons.validation.vk.VkUrl;

import java.util.List;

@Schema(name = "Данные формы заявки на участие по олимпиаде \"Навстречу Агротеху\"")
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public record AgrotechOlympiadRegistrationDto (

    @Schema(description = "ФИО отправителя вопроса")
    @NotBlank(message = "{common.error.validation.full-name.missing}")
    @Size(max = 255, message = "{common.error.validation.full-name.max.length.exceeded}")
    String applicantName,

    @Schema(description = "Контактный номер телефона")
    @NotBlank(message = "{common.error.validation.phone.missing}")
    @Phone(message = "{common.error.validation.phone.format}")
    String phone,

    @Schema(description = "Адрес электронной почты")
    @NotBlank(message = "{common.error.validation.email.missing}")
    @Email(message = "{common.error.validation.email.format}")
    String email,

    @Schema(description = "Ссылка на профиль социальной сети в контакте")
    @VkUrl(message = "{common.error.validation.vk.url.bad.format}")
    String vkProfileUrl,

    @Schema(description = "Идентификаторы направлений")
    @NotEmpty(message = "{agrotech.olympiad.registration.validation.error.directions.missing}")
    List<String> directionIds,

    @Schema(description = "Идентификатор ВУЗа из справочника")
    @NotBlank(message = "{agrotech.olympiad.registration.validation.error.university.missing}")
    String universityId
) {
}
