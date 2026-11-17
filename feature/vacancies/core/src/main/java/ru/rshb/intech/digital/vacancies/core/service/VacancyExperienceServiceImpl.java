package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.vacancies.core.repository.ExperienceRepository;
import ru.rshb.intech.digital.vacancies.entity.Experience;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyExperienceServiceImpl implements VacancyExperienceService {

  private static final String RESET_EXP_ID_SEQ_SQL =
      """
      alter sequence if exists digital_vacancies.o_plt_experience_id_seq
      restart with 1;
      """;

  private final EntityManager entityManager;
  private final ExperienceRepository experienceRepository;


  @Override
  public void deleteAll() {
    experienceRepository.deleteAll();
    resetSequences();
    entityManager.flush();
  }

  private void resetSequences() {
    entityManager
        .createNativeQuery(RESET_EXP_ID_SEQ_SQL)
        .executeUpdate();
  }

  @Nonnull
  @Override
  public List<Experience> findAll(@Nonnull Collection<String> externalId) {
    return experienceRepository.findAllByExternalIdInAndDeletedIsFalse(new HashSet<>(externalId));
  }

  @Nonnull
  @Override
  public List<Experience> findAll() {
    return experienceRepository.findAllByDeletedIsFalse();
  }

  @Nonnull
  @Override
  public List<Experience> saveAll(@Nonnull Collection<Experience> experiences) {
    return experienceRepository.saveAll(experiences);
  }
}
