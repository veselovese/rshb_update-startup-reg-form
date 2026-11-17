package ru.rshb.intech.digital.feature.contests.chef.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.rshb.intech.digital.commons.validation.url.UrlUtils;
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestUrlResult;
import ru.rshb.intech.digital.feature.contests.chef.exception.ChefSolutionValidationException;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

/**
 * Рассчитывает результат участника конкурса.
 */
@Component
@RequiredArgsConstructor
public class ChefContestResultValidator {

  private static final int SCORES_MIN = 0;
  private static final int SCORES_MAX = 5; // не более 5 очков

  private final RestTemplate restTemplate;


  public int getCheckLevel(@Nonnull String urlForCheck) {
    final var solutionData = getSolutionData(urlForCheck);
    return getSolutionScores(solutionData);
  }

  private int getSolutionScores(@Nonnull ChefContestUrlResult solutionData) {

    final var productIds = ObjectUtils.defaultIfNull(
        solutionData.getRelevantProductIds(),
        Collections.emptyList()
    );

    final int scores = productIds.size();

    return Math.min(scores, SCORES_MAX);
  }

  @Nonnull
  private ChefContestUrlResult getSolutionData(@Nonnull String url) {
    final var uri = validateUrl(url);
    final var result = fetchSolutionData(uri);
    return Optional.ofNullable(result)
        .orElseThrow(ChefSolutionValidationException::solutionUrlBadResponse);
  }

  @Nonnull
  private URI validateUrl(@Nonnull String url) {
    return UrlUtils.createUriIfValid(url)
        .orElseThrow(ChefSolutionValidationException::invalidSolutionUrl);
  }

  @Nullable
  private ChefContestUrlResult fetchSolutionData(@Nonnull URI solutionUrl) {
    try {
      final var json = restTemplate.getForObject(solutionUrl, ChefContestUrlResult.class);
      return (json != null && json.getFarmerId() != null && json.getRelevantProductIds() != null)
          ? json
          : null;
    } catch (ResourceAccessException rae) {
      throw ChefSolutionValidationException.solutionUnavailable(rae);
    } catch (Exception e) {
      throw ChefSolutionValidationException.solutionCheckError(e);
    }
  }

  public int calcResultInPercents(int scores) {

    if (scores < SCORES_MIN) {
      return SCORES_MIN;
    }

    return switch (scores) {
      case 0 -> 0;
      case 1 -> 20;
      case 2 -> 40;
      case 3 -> 60;
      case 4 -> 80;
      case 5 -> 100;
      default -> 100;
    };
  }
}
