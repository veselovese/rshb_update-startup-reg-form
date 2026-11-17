package ru.rshb.intech.digital.commons.utils.attachment;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Attachments {

  @Nonnull
  public static MultipartFileList list() {
    return new MultipartFileList();
  }


  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class MultipartFileList {

    private final List<Attachment> filesList = new ArrayList<>();

    @Nonnull
    public MultipartFileList ofNonNull(@Nonnull MultipartFile multipartFile, @Nonnull String[] allowedExtensions) {
      filesList.add(
          Attachment.fromMultipart(multipartFile, allowedExtensions)
      );
      return this;
    }

    @Nonnull
    public MultipartFileList ofNullable(@Nullable MultipartFile multipartFile, @Nonnull String[] allowedExtensions) {
      filesList.add(
          Attachment.fromNullableMultipartFile(multipartFile, allowedExtensions)
      );
      return this;
    }

    @Nonnull
    public List<Attachment> collectNonNull() {
      return filesList.stream()
          .filter(Objects::nonNull)
          .toList();
    }
  }
}
