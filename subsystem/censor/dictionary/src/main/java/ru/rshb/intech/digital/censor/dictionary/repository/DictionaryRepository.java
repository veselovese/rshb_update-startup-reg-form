package ru.rshb.intech.digital.censor.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.censor.dictionary.entity.WordEntity;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface DictionaryRepository extends JpaRepository<WordEntity, Long> {

  @Nonnull
  List<WordEntity> findAllByEnabledIsTrueOrderByRootAsc();
}
