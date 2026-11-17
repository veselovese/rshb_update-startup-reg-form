package ru.rshb.intech.digital.feature.contests.chef.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;
import ru.rshb.intech.digital.feature.contests.chef.repository.ChefContestantRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChefContestServiceImpl implements ChefContestService {

  private final ChefContestantRepository contestantRepository;
  private final ChefContestResultValidator resultValidator;


  @Override
  public boolean isSolutionExistByPhone(@Nonnull String phone) {
    return contestantRepository.existsByPhone(phone);
  }

  @Override
  public boolean isSolutionExistByUrl(@Nonnull String solutionUrl) {
    return contestantRepository.existsBySolutionUrl(solutionUrl);
  }

  @Nonnull
  @Override
  public ChefContestant save(@Nonnull ChefContestant chefContestant) {
    chefContestant.setSubmitDate(LocalDateTime.now());
    final var savedContestant = contestantRepository.save(chefContestant);
    log.info("New chef contestant has been saved");
    return savedContestant;
  }


  @Override
  public int getCheckLevel(@Nonnull String urlForCheck) {
    return resultValidator.getCheckLevel(urlForCheck);
  }
}
