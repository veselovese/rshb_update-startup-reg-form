package ru.rshb.intech.digital.feature.innovations.core.service.general.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.innovations.core.dto.GeneralRequestDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.GeneralRequest;
import ru.rshb.intech.digital.feature.innovations.core.exception.GeneralRequestValidationException;
import ru.rshb.intech.digital.feature.innovations.core.service.general.GeneralRequestService;
import ru.rshb.intech.digital.feature.innovations.core.service.general.form.GeneralRequestForm;

/**
 * Атрибут "Номер телефона".
 * Форма заявки с предложением или запросом инноваций.
 */
@Component("innovationsGeneralRequestFormPhone")
@FormAttribute(GeneralRequestForm.class)
public class Phone extends PhoneAttributeBase<GeneralRequestDto, GeneralRequest> {

  private static final int PROCESS_ORDER = 1;


  public Phone(@Nonnull GeneralRequestService generalRequestService) {
    super(new DuplicateValidator(generalRequestService));
  }


  @Override
  public String supplyInputValue(@Nonnull GeneralRequestDto inputData) {
    return inputData.getPhone();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull GeneralRequest outputData) {
    outputData.setPhone(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }

  @RequiredArgsConstructor
  private static class DuplicateValidator
      implements BusinessRulesValidator<GeneralRequestDto, GeneralRequest, String> {

    private final GeneralRequestService generalRequestService;

    @Override
    public void validate(String phone, @Nonnull FormContext<GeneralRequestDto, GeneralRequest> context)
        throws AttributeBusinessRulesException {

      if (generalRequestService.isRequestExist(phone)) {
        throw GeneralRequestValidationException.requestAlreadyExist();
      }
    }
  }
}
