package ru.rshb.intech.digital.feature.downloads.core.service.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.feature.downloads.core.service.form.FileRequestForm;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;

@Component("fileRequestFormUserName")
@FormAttribute(FileRequestForm.class)
public class Name extends ApplicantNameAttributeBase<FileRequestDto, FileRequest> {

  private static final int MAX_LENGTH = 100;

  public Name() {
    super(MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull FileRequestDto inputData) {
    return inputData.getName();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull FileRequest outputData) {
    outputData.setFullName(outputValue);
  }
}
