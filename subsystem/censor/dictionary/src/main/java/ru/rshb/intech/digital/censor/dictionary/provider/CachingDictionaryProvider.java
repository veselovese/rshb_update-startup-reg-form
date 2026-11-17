package ru.rshb.intech.digital.censor.dictionary.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.dictionary.event.InvalidateCensorDictionaryCacheEvent;
import ru.rshb.intech.digital.censor.dictionary.model.Dictionary;
import ru.rshb.intech.digital.censor.dictionary.provider.loader.DictionaryLoader;

import jakarta.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
@RequiredArgsConstructor
public class CachingDictionaryProvider implements DictionaryProvider {

  private final DictionaryLoader dictionaryLoader;
  private final AtomicBoolean needInvalidationRef = new AtomicBoolean(true);
  private final AtomicReference<Dictionary> dictionaryRef = new AtomicReference<>();

  @Nonnull
  @Override
  public Dictionary provide() {

    if (needInvalidationRef.compareAndSet(true, false)) {
      invalidateCache();
    }

    return dictionaryRef.get();
  }

  private void invalidateCache() {
    log.info("Invalidating censor dictionary cache...");
    try {
      final var freshDictionary = dictionaryLoader.load();
      dictionaryRef.set(freshDictionary);
      log.info("Censor dictionary cache invalidated");
    } catch (Exception e) {
      log.error("Censor dictionary cache invalidation error", e);
    }
  }

  @EventListener(InvalidateCensorDictionaryCacheEvent.class)
  public void handleDictionaryCacheInvalidationEvent() {
    needInvalidationRef.compareAndSet(false, true);
    log.info("Censor dictionary cache invalidation engaged");
  }
}
