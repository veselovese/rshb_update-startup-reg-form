package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.vacancies.core.repository.EmploymentRepository;
import ru.rshb.intech.digital.vacancies.entity.Employment;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyEmploymentServiceImpl implements VacancyEmploymentService {

  private static final String RESET_EMPLOYMENTS_ID_SEQ_SQL =
      """
      alter sequence if exists digital_vacancies.o_plt_employments_id_seq
      restart with 1;
      """;

  private final EntityManager entityManager;
  private final EmploymentRepository employmentRepository;


  @Override
  public void deleteAll() {
    employmentRepository.deleteAll();
    resetSequences();
    entityManager.flush();
  }

  private void resetSequences() {
    entityManager
        .createNativeQuery(RESET_EMPLOYMENTS_ID_SEQ_SQL)
        .executeUpdate();
  }

  @Nonnull
  @Override
  public List<Employment> findAll(@Nonnull Collection<String> externalIds) {
    return employmentRepository.findAllByExternalIdInAndDeletedIsFalse(new HashSet<>(externalIds));
  }

  @Nonnull
  @Override
  public List<Employment> findAll() {
    return employmentRepository.findAllByDeletedIsFalse();
  }

  @Nonnull
  @Override
  public List<Employment> saveAll(@Nonnull Collection<Employment> employments) {
    return employmentRepository.saveAll(employments);
  }
}
