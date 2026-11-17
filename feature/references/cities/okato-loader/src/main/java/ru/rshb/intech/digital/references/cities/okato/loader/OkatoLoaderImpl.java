package ru.rshb.intech.digital.references.cities.okato.loader;

import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.web.WebUtils;
import ru.rshb.intech.digital.references.cities.okato.loader.exception.OkatoLoaderException;
import ru.rshb.intech.digital.references.cities.okato.loader.reader.OkatoReader;

import jakarta.annotation.Nonnull;

@RequiredArgsConstructor
public class OkatoLoaderImpl implements OkatoLoader {

  private final OkatoReader reader;

  @Override
  public long load(@Nonnull LoaderOptions options) {
    try {
      final var localFile = WebUtils.download(options.getFileUrl(), options.getLocalPath());
      return reader.read(localFile);
    } catch (Exception e) {
      throw new OkatoLoaderException("OKATO loading error", e);
    }
  }
}
