package ru.rshb.intech.digital.service.content.vacancy.job;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.Setter;
import ru.rshb.intech.digital.vacancies.core.service.LookupCache;
import ru.rshb.intech.digital.vacancies.core.service.ProfRoleLookup;
import ru.rshb.intech.digital.vacancies.core.service.ReferenceEntityCache;
import ru.rshb.intech.digital.vacancies.hh.client.HHAccount;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.VacancyExtendedDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.list.VacancyItemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class VacancyJobContext {

  private final List<HHAccount> accounts = new ArrayList<>();
  private final List<VacancyItemDto> vacancyItems = new ArrayList<>();
  private final LookupCache lookupCache;
  private final ReferenceEntityCache referenceEntityCache = new ReferenceEntityCache();
  private final Map<String, VacancyExtendedDto> detailedVacancies = new ConcurrentHashMap<>();

  public VacancyJobContext(@Nonnull ProfRoleLookup profRoleReference) {
    this.lookupCache = new LookupCache(profRoleReference);
  }


  public void addAccounts(@Nonnull List<HHAccount> accounts) {
    this.accounts.addAll(accounts);
  }

  public void addVacancyItems(@Nonnull List<VacancyItemDto> vacancyItems) {
    this.vacancyItems.addAll(vacancyItems);
  }

  public void addDetailedVacancies(@Nonnull List<VacancyExtendedDto> providedVacancies) {
    for (final var vacancy : providedVacancies) {
      this.detailedVacancies.put(vacancy.getId(), vacancy);
    }
    this.lookupCache.merge(providedVacancies);
  }
}
