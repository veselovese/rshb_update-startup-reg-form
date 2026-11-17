package ru.rshb.intech.digital.feature.downloads.core.exception;

import lombok.Generated;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.messages.I18n;

@Generated
public class RequestedFileNotFound extends AttributeBusinessRulesException {

  public RequestedFileNotFound() {
    super(
        I18n.of("file.download.request.validation.file.not.found")
    );
  }
}
