package ru.rshb.intech.digital.service.content.vacancy.job.detailed;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import ru.rshb.intech.digital.service.content.vacancy.job.VacancyJobContext;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.VacancyExtendedDto;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class VacancyDetailedWriter implements ItemWriter<VacancyExtendedDto> {

  private final VacancyJobContext context;


  @Override
  @SuppressWarnings("unchecked")
  public void write(@Nonnull Chunk<? extends VacancyExtendedDto> chunk) {
    final var items = chunk.getItems();
    log.debug("Writing {} extended vacancies...", items.size());
    context.addDetailedVacancies((List<VacancyExtendedDto>) items);
    log.info("Vacancies loaded: {}", context.getDetailedVacancies().size());
  }
}
