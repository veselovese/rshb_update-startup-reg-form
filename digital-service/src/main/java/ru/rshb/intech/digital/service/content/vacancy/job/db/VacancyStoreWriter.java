package ru.rshb.intech.digital.service.content.vacancy.job.db;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import ru.rshb.intech.digital.vacancies.core.service.VacancyService;
import ru.rshb.intech.digital.vacancies.entity.Vacancy;

import java.util.Collection;

@SuppressWarnings("unchecked")
@Slf4j
@RequiredArgsConstructor
public class VacancyStoreWriter implements ItemWriter<Vacancy> {

  private final VacancyService vacancyService;

  @Override
  public void write(@Nonnull Chunk<? extends Vacancy> chunk) {
    final var items = chunk.getItems();
    log.debug("Writing next {} vacancy items in DB...", items.size());
    vacancyService.saveAll((Collection<Vacancy>) items);
  }
}
