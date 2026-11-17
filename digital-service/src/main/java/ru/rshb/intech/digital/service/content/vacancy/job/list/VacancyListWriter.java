package ru.rshb.intech.digital.service.content.vacancy.job.list;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import ru.rshb.intech.digital.service.content.vacancy.job.VacancyJobContext;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.list.VacancyItemDto;

import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class VacancyListWriter implements ItemWriter<List<? extends VacancyItemDto>> {

  private final VacancyJobContext context;


  @Override
  @SuppressWarnings("unchecked")
  public void write(@Nonnull Chunk<? extends List<? extends VacancyItemDto>> chunk) {

    final var chunks = chunk.getItems();

    log.debug("Storing vacancies chunk of {} elements...", chunks.size());
    final var vacancies = chunks.stream()
        .flatMap(Collection::stream)
        .toList();
    log.debug("Vacancies to store in context: {}", vacancies.size());

    context.addVacancyItems((List<VacancyItemDto>) vacancies);
  }
}
