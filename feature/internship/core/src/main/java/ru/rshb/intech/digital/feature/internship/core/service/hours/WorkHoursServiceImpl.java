package ru.rshb.intech.digital.feature.internship.core.service.hours;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.internship.core.dto.WorkHoursDto;
import ru.rshb.intech.digital.feature.internship.core.mapper.WorkHoursMapper;
import ru.rshb.intech.digital.feature.internship.core.repository.hours.WorkHoursRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkHoursServiceImpl implements WorkHoursService {

  private final WorkHoursRepository repository;
  private final WorkHoursMapper mapper;

  @Nonnull
  @Override
  public List<WorkHoursDto> findEnabledWorkHours() {
    return mapper.fromEntities(
        repository.findAllByEnabledIsTrue()
    );
  }

  @Override
  public boolean isWorkHoursExist(@Nonnull String title) {
    return repository.existsByTitleIgnoreCase(title);
  }
}
