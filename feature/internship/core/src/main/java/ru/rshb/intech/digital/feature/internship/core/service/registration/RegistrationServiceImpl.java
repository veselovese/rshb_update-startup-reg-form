package ru.rshb.intech.digital.feature.internship.core.service.registration;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.repository.fields.InternshipFieldRepository;
import ru.rshb.intech.digital.feature.internship.core.repository.registration.RegistrationRepository;
import ru.rshb.intech.digtial.commons.dto.DistributionStatisticsDto;
import ru.rshb.intech.digtial.commons.dto.DistributionStatisticsDto.Item;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

  private static final String[] CUSTOM_ATTACHMENT_ALLOWED_EXT = {"ppt", "pptx", "doc", "docx", "pdf"};
  private static final String[] SKILL_PASSPORT_ALLOWED_EXT = {"pdf"};

  private final RegistrationRepository registrationRepository;
  private final InternshipFieldRepository fieldRepository;

  @Nonnull
  @Override
  @Transactional
  public Registration save(@Nonnull Registration registration) {
    registration.setRegistrationDate(LocalDateTime.now());
    final var savedRegistration = registrationRepository.save(registration);
    log.info("New internship registration has been received");
    return savedRegistration;
  }

  @Nonnull
  @Override
  @Transactional
  public DistributionStatisticsDto findDistributionByFields() { // FIXME: нужен 1 запрос
    final var fields = fieldRepository.findAllNonDeleted();
    final var statItems = new ArrayList<Item>();
    for (final var field : fields) {
      final double count = registrationRepository.getCountByField(field.getTitle());
      statItems.add(new Item(field.getTitle(), count));
    }
    return new DistributionStatisticsDto(statItems);
  }

  @Nonnull
  @Override
  public String[] getFormAttachmentAllowedExtensions() {
    return CUSTOM_ATTACHMENT_ALLOWED_EXT;
  }

  @Nonnull
  @Override
  public String[] getSkillPassportAllowedExtensions() {
    return SKILL_PASSPORT_ALLOWED_EXT;
  }
}
