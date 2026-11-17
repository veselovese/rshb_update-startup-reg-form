package ru.rshb.intech.digital.feature.contests.chef.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;

public interface ChefContestService {

  boolean isSolutionExistByPhone(@Nonnull String phone);

  boolean isSolutionExistByUrl(@Nonnull String solutionUrl);

  @Nonnull
  ChefContestant save(@Nonnull ChefContestant chefContestant);

  int getCheckLevel(@Nonnull String urlForCheck);
}
