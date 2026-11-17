package ru.rshb.intech.digital.service.content.vacancy.job.list;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import ru.rshb.intech.digital.service.content.vacancy.job.VacancyJobContext;
import ru.rshb.intech.digital.vacancies.hh.client.HHAccount;
import ru.rshb.intech.digital.vacancies.hh.client.HHClientService;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.list.VacancyItemDto;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
public class VacancyListReader implements ItemReader<List<VacancyItemDto>> {

  private final HHClientService hhClient;
  private final Queue<HHAccount> accounts;
  private HHAccount currentAccount;
  private int pageIndex;


  public VacancyListReader(@Nonnull HHClientService hhClient, @Nonnull VacancyJobContext context) {
    this.hhClient = hhClient;
    this.accounts = new LinkedList<>(context.getAccounts());
    this.pageIndex = 0;
  }


  @Override
  public List<VacancyItemDto> read() throws Exception {

    if (currentAccount == null) {
      log.debug("No current account for vacancies reading iteration. Trying to find first...");
      currentAccount = accounts.poll();
      if (currentAccount == null) {
        log.debug("No current account. Vacancies reading aborted");
        return null;
      }
      log.debug("Current account: {}", currentAccount.getName());
    }

    log.debug("Reading vacancies page {} for [{}] account...", pageIndex, currentAccount.getName());
    final var vacancies = hhClient.findAllVacanciesByPage(currentAccount, pageIndex++);
    log.debug("Found {} vacancies", vacancies.size());

    if (vacancies.isEmpty()) {
      log.debug("There is no vacancies returned. Trying to change account...");
      currentAccount = accounts.poll();
      if (currentAccount == null) {
        log.debug("No accounts found. Vacancies reading completed");
        return null;
      }
      pageIndex = 0;
      log.debug("Current account: {} at page {}", currentAccount.getName(), pageIndex);
    }

    return vacancies;
  }
}
