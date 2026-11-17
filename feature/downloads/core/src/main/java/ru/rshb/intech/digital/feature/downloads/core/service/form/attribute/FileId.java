package ru.rshb.intech.digital.feature.downloads.core.service.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.downloads.core.exception.RequestedFileNotFound;
import ru.rshb.intech.digital.feature.downloads.core.service.FileDownloadService;
import ru.rshb.intech.digital.feature.downloads.core.service.form.FileRequestForm;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;
import ru.rshb.intech.digital.subsystem.content.files.service.FileIndexService;

@Component("fileRequestFormFileId")
@FormAttribute(FileRequestForm.class)
public class FileId extends TextAttributeBase<FileRequestDto, FileRequest> {

  private static final String TITLE_KEY = "file.download.request.attr.form-id.name";
  private static final int MAX_LENGTH = 255;
  private static final int PROCESS_ORDER = 1;
  private final FileIndexService fileIndexService;


  public FileId(@Nonnull FileDownloadService fileDownloadService,
                @Nonnull FileIndexService fileIndexService) {

    super(TITLE_KEY, MAX_LENGTH, new FileIdValidator(fileDownloadService));
    this.fileIndexService = fileIndexService;
  }

  @Override
  public String supplyInputValue(FileRequestDto inputData) {
    return inputData.getFileId();
  }

  @Override
  public void consumeOutputValue(String fileExternalId, FileRequest outputData) {
    final var file = fileIndexService.getFileByExternalId(fileExternalId);
    outputData.setFile(file);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }

  @RequiredArgsConstructor
  private static class FileIdValidator implements BusinessRulesValidator<FileRequestDto, FileRequest, String> {

    private final FileDownloadService fileDownloadService;

    @Override
    public void validate(String fileId, @Nonnull FormContext<FileRequestDto, FileRequest> context)
        throws AttributeBusinessRulesException {

      if (!fileDownloadService.isFileExist(fileId)) {
        throw new RequestedFileNotFound();
      }
    }
  }
}
