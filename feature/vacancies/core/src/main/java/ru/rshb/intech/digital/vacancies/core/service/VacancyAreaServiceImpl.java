package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.vacancies.core.repository.AreaRepository;
import ru.rshb.intech.digital.vacancies.entity.Area;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyAreaServiceImpl implements VacancyAreaService {

  private static final String RESET_AREAS_ID_SEQ_SQL =
      """
      alter sequence if exists digital_vacancies.o_plt_areas_id_seq
      restart with 1;
      """;

  private final EntityManager entityManager;
  private final AreaRepository areaRepository;


  @Override
  public void deleteAll() {
    areaRepository.deleteAll();
    resetSequences();
    entityManager.flush();
  }

  private void resetSequences() {
    entityManager
        .createNativeQuery(RESET_AREAS_ID_SEQ_SQL)
        .executeUpdate();
  }

  @Nullable
  @Override
  public Area getByExternalIdOrNull(@Nonnull String externalId) {
    return areaRepository.findByExternalIdAndDeletedIsFalse(externalId)
        .orElse(null);
  }

  @Nonnull
  @Override
  public List<Area> findAll() {
    return areaRepository.findAllByDeletedIsFalse();
  }

  @Nonnull
  @Override
  public List<Area> saveAll(@Nonnull Collection<Area> areas) {
    return areaRepository.saveAll(areas);
  }
}
