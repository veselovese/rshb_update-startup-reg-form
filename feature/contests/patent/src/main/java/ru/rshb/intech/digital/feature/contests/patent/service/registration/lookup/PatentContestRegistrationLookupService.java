package ru.rshb.intech.digital.feature.contests.patent.service.registration.lookup;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationLookupDto;

public interface PatentContestRegistrationLookupService {

  @Nonnull
  PatentContestRegistrationLookupDto getLookups();

}
