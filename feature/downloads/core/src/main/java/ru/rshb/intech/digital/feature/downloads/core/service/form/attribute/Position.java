package ru.rshb.intech.digital.feature.downloads.core.service.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.downloads.core.service.form.FileRequestForm;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;

@Component("fileRequestFormPosition")
@FormAttribute(FileRequestForm.class)
public class Position extends TextAttributeBase<FileRequestDto, FileRequest> {

  private static final String TITLE_KEY = "common.form.attribute.position.title";
  private static final int MAX_LENGTH = 100;


  public Position() {
    super(TITLE_KEY, true, MAX_LENGTH);
  }


  @Override
  public String supplyInputValue(@Nonnull FileRequestDto inputData) {
    return inputData.getPosition();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull FileRequest outputData) {
    outputData.setPosition(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
