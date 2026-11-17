package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.vacancies.core.repository.SpecializationRepository;
import ru.rshb.intech.digital.vacancies.entity.Specialization;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancySpecializationServiceImpl implements VacancySpecializationService {

  private static final String RESET_SPEC_ID_SEQ_SQL =
      """
      alter sequence if exists digital_vacancies.o_plt_specializations_id_seq
      restart with 1;
      """;

  private final EntityManager entityManager;
  private final SpecializationRepository specializationRepository;


  @Override
  public void deleteAll() {
    specializationRepository.deleteAll();
    resetSequences();
    entityManager.flush();
  }

  private void resetSequences() {
    entityManager
        .createNativeQuery(RESET_SPEC_ID_SEQ_SQL)
        .executeUpdate();
  }

  @Nonnull
  @Override
  public List<Specialization> findAll(@Nonnull Collection<String> externalIds) {
    return specializationRepository.findAllByExternalIdInAndDeletedIsFalse(new HashSet<>(externalIds));
  }

  @Nonnull
  @Override
  public List<Specialization> findAll() {
    return specializationRepository.findAllByDeletedIsFalse();
  }

  @Nonnull
  @Override
  public List<Specialization> saveAll(@Nonnull Collection<Specialization> specializations) {
    final var savedItems = specializationRepository.saveAll(specializations);
    entityManager.flush();
    return savedItems;
  }
}
