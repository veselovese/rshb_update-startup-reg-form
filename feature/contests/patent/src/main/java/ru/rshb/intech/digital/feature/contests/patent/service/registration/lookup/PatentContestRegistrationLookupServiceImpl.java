package ru.rshb.intech.digital.feature.contests.patent.service.registration.lookup;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.contests.patent.dto.CityDto;
import ru.rshb.intech.digital.feature.contests.patent.dto.ClientNumberDto;
import ru.rshb.intech.digital.feature.contests.patent.dto.CommercialImplementationDto;
import ru.rshb.intech.digital.feature.contests.patent.dto.FinancingRoundDto;
import ru.rshb.intech.digital.feature.contests.patent.dto.ManagingSubjectDto;
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationLookupDto;
import ru.rshb.intech.digital.feature.contests.patent.dto.ProjectStageDto;
import ru.rshb.intech.digital.feature.contests.patent.repository.ClientNumberRepository;
import ru.rshb.intech.digital.feature.contests.patent.repository.CommercialImplRepository;
import ru.rshb.intech.digital.feature.contests.patent.repository.FinancingRoundRepository;
import ru.rshb.intech.digital.feature.contests.patent.repository.ManagingSubjectRepository;
import ru.rshb.intech.digital.feature.contests.patent.repository.PatentDefCityRepository;
import ru.rshb.intech.digital.feature.contests.patent.repository.ProjectStageRepository;

@Service
@RequiredArgsConstructor
public class PatentContestRegistrationLookupServiceImpl implements PatentContestRegistrationLookupService {

  private final ClientNumberRepository clientNumberRepository;
  private final CommercialImplRepository commercialImplRepository;
  private final FinancingRoundRepository financingRoundRepository;
  private final ManagingSubjectRepository managingSubjectRepository;
  private final ProjectStageRepository projectStageRepository;
  private final PatentDefCityRepository cityRepository;
  private final CommonLookupMapper mapper;

  @Nonnull
  @Override
  public PatentContestRegistrationLookupDto getLookups() {

    return PatentContestRegistrationLookupDto.builder()
        .cities(
            mapper.map(cityRepository.findAllByDisabledIsFalseOrderById(), CityDto::new)
        )
        .commercialImpls(
            mapper.map(commercialImplRepository.findAllByDisabledIsFalse(), CommercialImplementationDto::new)
        )
        .clientNumberVariants(
            mapper.map(clientNumberRepository.findAllByDisabledIsFalse(), ClientNumberDto::new)
        )
        .managingSubjects(
            mapper.map(managingSubjectRepository.findAllByDisabledIsFalse(), ManagingSubjectDto::new)
        )
        .projectStages(
            mapper.map(projectStageRepository.findAllByDisabledIsFalse(), ProjectStageDto::new)
        )
        .financingRounds(
            mapper.map(financingRoundRepository.findAllByDisabledIsFalse(), FinancingRoundDto::new)
        )
        .build();
  }
}
