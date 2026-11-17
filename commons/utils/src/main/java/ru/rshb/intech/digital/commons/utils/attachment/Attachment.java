package ru.rshb.intech.digital.commons.utils.attachment;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ru.rshb.intech.digital.commons.utils.Preconditions.checkNotNull;
import static ru.rshb.intech.digital.commons.utils.attachment.AttachmentException.fileMissingError;
import static ru.rshb.intech.digital.commons.utils.attachment.AttachmentException.fileReadingError;
import static ru.rshb.intech.digital.commons.utils.attachment.AttachmentException.unacceptableFileTypeError;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Attachment {

  private final String originalFilename;
  private final byte[] data;
  private final String contentType;

  @Nonnull
  public static Attachment create(@Nonnull String fileName, @Nonnull String contentType, @Nonnull byte[] data) {
    return new Attachment(fileName, data, contentType);
  }

  @Nonnull
  public static Attachment fromMultipart(MultipartFile multipartFile, @Nonnull String... validExtensions) {

    checkNotNull(validExtensions, "No valid extensions specified");

    final var extensionsSet = new HashSet<>(Arrays.asList(validExtensions));
    return fromMultipart(multipartFile, extensionsSet);
  }

  @Nonnull
  public static Attachment fromMultipart(MultipartFile multipartFile, @Nonnull Set<String> validExtensions) {

    assertMultipartNotBlank(multipartFile);
    assertFileNameNotBlank(multipartFile.getOriginalFilename());

    if (CollectionUtils.isNotEmpty(validExtensions)) {
      assertValidExtension(multipartFile.getOriginalFilename(), validExtensions);
    }

    try {
      return new Attachment(
          multipartFile.getOriginalFilename(),
          multipartFile.getBytes(),
          multipartFile.getContentType()
      );
    } catch (IOException e) {
      throw fileReadingError(e);
    }
  }

  private static void assertMultipartNotBlank(@Nullable MultipartFile multipartFile) {
    if (isMultipartFileNotSpecified(multipartFile)) {
      throw fileMissingError();
    }
  }

  private static void assertFileNameNotBlank(@Nullable String fileName) {
    if (StringUtils.isBlank(fileName)) {
      throw unacceptableFileTypeError();
    }
  }

  private static void assertValidExtension(@Nullable String fileName, @Nonnull Set<String> validExtensions) {
    final boolean isValidExtension = FilenameUtils.isExtension(fileName, validExtensions);
    if (!isValidExtension) {
      throw unacceptableFileTypeError();
    }
  }

  @Nullable
  public static Attachment fromNullableMultipartFile(@Nullable MultipartFile multipartFile,
                                                     @Nonnull String... validExtensions) {

    final var extensionsSet = new HashSet<>(Arrays.asList(validExtensions));
    return fromNullableMultipartFile(multipartFile, extensionsSet); // FIXME: args check after memory alloc
  }

  @Nullable
  public static Attachment fromNullableMultipartFile(@Nullable MultipartFile multipartFile,
                                                     @Nonnull Set<String> validExtensions) {

    if (isMultipartFileNotSpecified(multipartFile)) {
      return null;
    }

    return fromMultipart(multipartFile, validExtensions);
  }

  private static boolean isMultipartFileNotSpecified(MultipartFile multipartFile) {
    return (multipartFile == null || multipartFile.isEmpty());
  }
}
