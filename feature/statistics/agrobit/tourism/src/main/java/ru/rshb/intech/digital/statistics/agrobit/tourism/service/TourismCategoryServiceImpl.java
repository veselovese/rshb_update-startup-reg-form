package ru.rshb.intech.digital.statistics.agrobit.tourism.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.statistics.agrobit.tourism.dto.RecreationTypeDto;
import ru.rshb.intech.digital.statistics.agrobit.tourism.dto.TourismTypeDto;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.RecreationType;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismType;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.TourismTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourismCategoryServiceImpl implements TourismCategoryService {

  private final TourismTypeRepository tourismTypeRepository;


  @Nonnull
  @Override
  @Transactional
  public List<TourismTypeDto> getAllCategories() {
    return tourismTypeRepository.findAll()
        .stream()
        .map(this::map)
        .toList();
  }

  @Nonnull
  private TourismTypeDto map(@Nonnull TourismType entity) {
    final var dto = new TourismTypeDto();
    dto.setId(entity.getExternalId());
    dto.setTitle(entity.getTitle());
    dto.setRecreationTypes(
        entity.getRecreationTypes()
            .stream()
            .map(this::map)
            .toList()
    );
    return dto;
  }

  @Nonnull
  private RecreationTypeDto map(@Nonnull RecreationType entity) {
    final var dto = new RecreationTypeDto();
    dto.setId(entity.getExternalId());
    dto.setTitle(entity.getTitle());
    return dto;
  }
}
