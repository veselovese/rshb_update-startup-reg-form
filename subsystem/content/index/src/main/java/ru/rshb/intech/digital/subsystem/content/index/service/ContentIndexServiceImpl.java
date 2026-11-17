package ru.rshb.intech.digital.subsystem.content.index.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.ItemType;
import ru.rshb.intech.digital.subsystem.content.index.entity.ContentIndexEntry;
import ru.rshb.intech.digital.subsystem.content.index.repository.ContentIndexRepository;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@Service
@RequiredArgsConstructor
public class ContentIndexServiceImpl implements ContentIndexService {

  private final ContentIndexRepository contentIndexRepository;

  @Cacheable("publicationIndexCache")
  @Nullable
  @Override
  public ContentIndexEntry getById(@Nonnull String id) {
    return contentIndexRepository.findById(id)
        .orElse(null);
  }

  @Nonnull
  @Override
  @Transactional
  public String putInIndex(@Nonnull String pubId, @Nonnull ItemType itemType) {

    final var searchResult = contentIndexRepository.findById(pubId);

    if (searchResult.isEmpty()) {
      return contentIndexRepository.save(new ContentIndexEntry(pubId, itemType))
          .getId();
    }

    final var existingEntry = searchResult.get();
    existingEntry.setItemType(itemType);

    return contentIndexRepository.save(existingEntry)
        .getId();
  }
}
