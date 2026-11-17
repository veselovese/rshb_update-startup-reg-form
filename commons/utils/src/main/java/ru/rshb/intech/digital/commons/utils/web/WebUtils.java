package ru.rshb.intech.digital.commons.utils.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.Nonnull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebUtils {

  @Nonnull
  public static File download(@Nonnull String url, @Nonnull String localPath) throws IOException {

    final var targetUrl = new URL(url);
    final var localFile = new File(localPath);

    log.debug("Downloading [{}] to [{}]", targetUrl, localFile);
    try (final var channel = Channels.newChannel(targetUrl.openStream());
         final var output = new FileOutputStream(localFile)) {

      final long fetchedBytes = output.getChannel()
          .transferFrom(channel, 0, Long.MAX_VALUE);

      log.debug("Fetched: {} bytes", fetchedBytes);
      return localFile;
    } catch (Exception e) {
      throw new IOException("Download error: " + url, e);
    }
  }
}
