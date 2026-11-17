package ru.rshb.intech.digital.references.cities.okato.loader;

import lombok.extern.slf4j.Slf4j;
import ru.rshb.intech.digital.references.cities.okato.loader.reader.OkatoCsvReader;
import ru.rshb.intech.digital.references.cities.okato.loader.writer.OkatoSqlWriter;

@Slf4j
public class OkatoLoaderApp {

  public static void main(String[] args) {

    if (args.length < 3) {
      throw new RuntimeException("No input parameters");
    }

    final var options = new LoaderOptions();
    options.setFileUrl(args[0]);
    options.setLocalPath(args[1]);

    final var writer = new OkatoSqlWriter(args[2]);
    final var reader = new OkatoCsvReader(writer);
    final var loader = new OkatoLoaderImpl(reader);


    final long processed = loader.load(options);
    log.info("Processed: {} items", processed);
  }

}
