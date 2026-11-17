package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.vacancies.entity.Company;
import ru.rshb.intech.digital.vacancies.hh.client.HHAccount;

import java.util.Collection;
import java.util.List;

public interface VacancyCompanyService {

  @Nonnull
  List<Company> findAllCompanies();

  @Nonnull
  List<HHAccount> findAllAccounts();

  @Nonnull
  List<Company> findAllCompanies(@Nonnull Collection<String> ids);
}
