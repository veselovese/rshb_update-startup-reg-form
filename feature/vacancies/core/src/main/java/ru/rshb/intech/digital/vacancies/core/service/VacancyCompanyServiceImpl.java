package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.vacancies.core.repository.HHCompanyRepository;
import ru.rshb.intech.digital.vacancies.entity.Company;
import ru.rshb.intech.digital.vacancies.hh.client.HHAccount;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyCompanyServiceImpl implements VacancyCompanyService {

  private final HHCompanyRepository companyRepository;

  @Nonnull
  @Override
  public List<Company> findAllCompanies() {
    return companyRepository.findAllByDeletedIsFalse();
  }

  @Nonnull
  @Override
  public List<HHAccount> findAllAccounts() {
    return companyRepository.findAllByDeletedIsFalse()
        .stream()
        .map(company -> new HHAccount(company.getExternalId(), company.getTitle()))
        .toList();
  }

  @Nonnull
  @Override
  public List<Company> findAllCompanies(@Nonnull Collection<String> ids) {
    return companyRepository.findAllByDeletedIsFalseAndExternalIdIn(ids);
  }
}
