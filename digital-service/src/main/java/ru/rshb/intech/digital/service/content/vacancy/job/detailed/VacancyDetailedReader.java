package ru.rshb.intech.digital.service.content.vacancy.job.detailed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import ru.rshb.intech.digital.service.content.vacancy.job.VacancyJobContext;
import ru.rshb.intech.digital.vacancies.hh.client.HHClientService;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.VacancyExtendedDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.list.VacancyItemDto;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
public class VacancyDetailedReader implements ItemReader<VacancyExtendedDto> {

  private final HHClientService hhClient;
  private final VacancyJobContext context;
  private Queue<VacancyItemDto> vacancyItems;


  @Override
  public VacancyExtendedDto read() throws Exception {

    if (vacancyItems == null) {
      vacancyItems = new LinkedList<>(context.getVacancyItems());
    }

    log.debug("Extracting vacancy item from context...");
    final var vacancyItem = vacancyItems.poll();
    if (vacancyItem == null) {
      log.debug("There is no vacancy items in context. Operation aborted");
      return null;
    }

    log.debug("Found vacancy item: {}", vacancyItem.getId());
    final var detailedVacancy = hhClient.getDetailedVacancyById(vacancyItem.getId());

    final var debugMsg = (detailedVacancy != null)
        ? String.format("Detailed vacancy found: %s", vacancyItem.getId())
        : String.format("No detailed vacancy found: %s", vacancyItem.getId());
    log.debug(debugMsg);

    return detailedVacancy;
  }
}