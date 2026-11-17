package ru.rshb.intech.digital.feature.downloads.core.service.form;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.censor.forms.CensorPreCheck;
import ru.rshb.intech.digital.commons.forms.Form;
import ru.rshb.intech.digital.commons.forms.attributes.provider.FormAttributeProvider;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;

@Component
public class FileRequestForm extends Form<FileRequestDto, FileRequest> {

  public static final String TITLE_KEY = "file.download.form.name";

  private final DefaultFileRequestHandler defaultFileRequestHandler;

  public FileRequestForm(FormAttributeProvider attributeProvider,
                         CensorAnalyzer censorAnalyzer,
                         DefaultFileRequestHandler defaultFileRequestHandler) {
    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));
    this.defaultFileRequestHandler = defaultFileRequestHandler;
  }

  @Nonnull
  @Override
  public FileRequest process(@Nonnull FileRequestDto inputData) {
    return process(inputData, FileRequest::new, defaultFileRequestHandler);
  }

  @Nonnull
  public FileRequest process(@Nonnull FileRequestDto inputData, @Nonnull FileRequestResultHandler resultHandler) {
    return process(inputData, FileRequest::new, resultHandler);
  }
}
