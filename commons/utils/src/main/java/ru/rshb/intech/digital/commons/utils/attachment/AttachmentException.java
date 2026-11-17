package ru.rshb.intech.digital.commons.utils.attachment;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.utils.exceptiion.DigitalServiceException;
import ru.rshb.intech.digital.messages.I18n;

public class AttachmentException extends DigitalServiceException {

  private AttachmentException(String message) {
    super(message);
  }

  private AttachmentException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Ошибка чтения файла.
   *
   * @param throwable Исходное исключение.
   * @return Исключение.
   */
  @Nonnull
  public static AttachmentException fileReadingError(@Nonnull Throwable throwable) {
    return new AttachmentException(
        I18n.of("attachment.error.reading"), throwable
    );
  }

  /**
   * Не указан файл.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttachmentException fileMissingError() {
    return new AttachmentException(
        I18n.of("attachment.error.missing")
    );
  }

  /**
   * Недопустимый тип файла.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttachmentException unacceptableFileTypeError() {
    return new AttachmentException(
        I18n.of("attachment.error.extension.not.acceptable")
    );
  }
}
