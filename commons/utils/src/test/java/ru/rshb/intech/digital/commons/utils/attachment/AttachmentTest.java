package ru.rshb.intech.digital.commons.utils.attachment;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AttachmentTest {

  private final String[] validExtensions = {"ppt", "pptx", "pdf", "doc", "docx"};

  @Test
  void fromMultipartFile() {

    final var originalFileName = "cv.pdf";
    final byte[] data = "Повар сказал повару...".getBytes(StandardCharsets.UTF_8);
    final var fileMock = new MockMultipartFile(
        "attachment",
        originalFileName,
        MediaType.APPLICATION_PDF_VALUE,
        data
    );

    Attachment attachment = Attachment.fromMultipart(fileMock, validExtensions);
    assertNotNull(attachment);
    assertEquals(MediaType.APPLICATION_PDF_VALUE, attachment.getContentType());
    assertEquals(data, attachment.getData());
    assertEquals(originalFileName, attachment.getOriginalFilename());
  }

  @Test
  void fromMultipartFile_invalid_extension() {

    final var originalFileName = "cv.txt";
    final byte[] data = "Повар сказал повару...".getBytes(StandardCharsets.UTF_8);
    final var fileMock = new MockMultipartFile(
        "attachment",
        originalFileName,
        MediaType.TEXT_PLAIN_VALUE,
        data
    );

    assertThrows(AttachmentException.class, () -> {
      Attachment.fromMultipart(fileMock, validExtensions);
    });
  }

  @Test
  void fromMultipartFile_no_extensions() {

    final var originalFileName = "cv.txt";
    final byte[] data = "Повар сказал повару...".getBytes(StandardCharsets.UTF_8);
    final var fileMock = new MockMultipartFile(
        "attachment",
        originalFileName,
        MediaType.TEXT_PLAIN_VALUE,
        data
    );

    Attachment attachment = Attachment.fromMultipart(fileMock);
    assertNotNull(attachment);
    assertEquals(MediaType.TEXT_PLAIN_VALUE, attachment.getContentType());
    assertEquals(data, attachment.getData());
    assertEquals(originalFileName, attachment.getOriginalFilename());
  }

  @Test
  void fromMultipartFile_reading_error() {

    MultipartFile fileMock = new MultipartFile() {
      @Override
      public String getName() {
        return "fileMock";
      }

      @Override
      public String getOriginalFilename() {
        return "fileMock.txt";
      }

      @Override
      public String getContentType() {
        return MediaType.TEXT_PLAIN_VALUE;
      }

      @Override
      public boolean isEmpty() {
        return false;
      }

      @Override
      public long getSize() {
        return 100500;
      }

      @Override
      public byte[] getBytes() throws IOException {
        throw new IOException("reading error mock");
      }

      @Override
      public InputStream getInputStream() throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
      }

      @Override
      public void transferTo(File dest) throws IOException, IllegalStateException {
      }
    };

    AttachmentException exception = null;
    try {
      Attachment.fromMultipart(fileMock, validExtensions);
    } catch (AttachmentException e) {
      exception = e;
    }

    assertNotNull(exception);
  }

  @Test
  void fromMultipartFile_null() {
    assertThrows(AttachmentException.class, () -> {
      Attachment.fromMultipart(null, Collections.emptySet());
    });
  }

  @Test
  void fromMultipartFile_empty() {

    final var fileMock = new MockMultipartFile(
        "name",
        "",
        MediaType.TEXT_PLAIN_VALUE,
        new byte[]{}
    );

    assertThrows(AttachmentException.class, () -> {
      Attachment.fromMultipart(fileMock, validExtensions);
    });
  }


  @Test
  void fromNullableMultipartFile_null() {
    Attachment attachment = Attachment.fromNullableMultipartFile(null, Collections.emptySet());
    assertNull(attachment);
  }

  @Test
  void fromNullableMultipartFile_non_null() {
    final var originalFileName = "cv.pdf";
    final byte[] data = "Повар сказал повару...".getBytes(StandardCharsets.UTF_8);
    final var fileMock = new MockMultipartFile(
        "attachment",
        originalFileName,
        MediaType.APPLICATION_PDF_VALUE,
        data
    );
    Attachment attachment = Attachment.fromNullableMultipartFile(fileMock, validExtensions);
    assertNotNull(attachment);
    assertEquals(MediaType.APPLICATION_PDF_VALUE, attachment.getContentType());
    assertEquals(data, attachment.getData());
    assertEquals(originalFileName, attachment.getOriginalFilename());
  }

  @Test
  void testEquals() {
    final var originalFileName = "cv.pdf";
    final var fileMock1 = new MockMultipartFile(
        "attachment",
        originalFileName,
        MediaType.APPLICATION_PDF_VALUE,
        "Повар сказал повару...".getBytes(StandardCharsets.UTF_8)
    );
    final var fileMock2 = new MockMultipartFile(
        "attachment",
        originalFileName,
        MediaType.APPLICATION_PDF_VALUE,
        "Повар сказал повару...".getBytes(StandardCharsets.UTF_8)
    );
    Attachment attachment1 = Attachment.fromMultipart(fileMock1, validExtensions);
    Attachment attachment2 = Attachment.fromMultipart(fileMock2, validExtensions);

    assertEquals(attachment1, attachment2);
  }

  @Test
  void testHashCode() {

    final var originalFileName = "cv.pdf";
    final var fileMock1 = new MockMultipartFile(
        "attachment",
        originalFileName,
        MediaType.APPLICATION_PDF_VALUE,
        "Повар сказал повару...".getBytes(StandardCharsets.UTF_8)
    );
    final var fileMock2 = new MockMultipartFile(
        "attachment",
        originalFileName,
        MediaType.APPLICATION_PDF_VALUE,
        "Повар сказал повару...".getBytes(StandardCharsets.UTF_8)
    );
    Attachment attachment1 = Attachment.fromMultipart(fileMock1, validExtensions);
    Attachment attachment2 = Attachment.fromMultipart(fileMock2, validExtensions);

    assertEquals(attachment1.hashCode(), attachment2.hashCode());
  }
}