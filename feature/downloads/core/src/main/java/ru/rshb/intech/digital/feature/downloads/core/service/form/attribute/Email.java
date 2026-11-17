package ru.rshb.intech.digital.feature.downloads.core.service.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.feature.downloads.core.service.form.FileRequestForm;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;

@Component("fileRequestFormEmail")
@FormAttribute(FileRequestForm.class)
public class Email extends EmailAttributeBase<FileRequestDto, FileRequest> {

  private static final int PROCESS_ORDER = 3;

  @Override
  public String supplyInputValue(@Nonnull FileRequestDto inputData) {
    return inputData.getEmail();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull FileRequest outputData) {
    outputData.setEmail(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }
}
