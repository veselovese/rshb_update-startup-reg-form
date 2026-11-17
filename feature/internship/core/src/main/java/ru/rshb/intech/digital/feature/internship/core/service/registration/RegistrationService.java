package ru.rshb.intech.digital.feature.internship.core.service.registration;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digtial.commons.dto.DistributionStatisticsDto;

public interface RegistrationService {

  @Nonnull
  Registration save(@Nonnull Registration registration);

  @Nonnull
  DistributionStatisticsDto findDistributionByFields();

  @Nonnull
  String[] getFormAttachmentAllowedExtensions();

  @Nonnull
  String[] getSkillPassportAllowedExtensions();
}
