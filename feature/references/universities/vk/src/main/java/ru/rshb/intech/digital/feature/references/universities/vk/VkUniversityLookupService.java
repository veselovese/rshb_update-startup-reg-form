package ru.rshb.intech.digital.feature.references.universities.vk;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.VkApiClient;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.references.universities.vk.exception.VkUniversityLookupServiceException;
import ru.rshb.intech.digital.references.universities.api.University;
import ru.rshb.intech.digital.references.universities.api.UniversityLookupService;
import ru.rshb.intech.digital.subsystems.integrations.vk.config.VkAuth;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

@Slf4j
@Service
@RequiredArgsConstructor
public class VkUniversityLookupService implements UniversityLookupService {

  private static final int DEFAULT_LIMIT = 10;

  private final VkApiClient vkApiClient;
  private final VkAuth vkAuth;

  private boolean isNotAuthProvided() { // TODO
    if (vkAuth.isDisabled()) {
      log.warn("VK auth is not provided");
    }
    return vkAuth.isDisabled();
  }

  @Nonnull
  @Override
  public List<University> findUniversityByTitle(@Nonnull String universityTitle) {

    if (isNotAuthProvided()) { // TODO
      return Collections.emptyList();
    }

    return findUniversityByTitle(universityTitle, DEFAULT_LIMIT);
  }

  @Nonnull
  @Override
  public List<University> findUniversityByTitle(@Nonnull String universityTitle, int limit) {

    if (isNotAuthProvided()) { // TODO
      return Collections.emptyList();
    }

    if (StringUtils.isBlank(universityTitle)) {
      return emptyList();
    }

    final var trimmedUniversityTitle = universityTitle.trim();
    if (trimmedUniversityTitle.length() < 3) {
      return emptyList();
    }

    try {

      final var response = vkApiClient.database()
          .getUniversities(vkAuth.getServiceActor())
          .q(trimmedUniversityTitle)
          .lang(Lang.RU)
          .cityId(1)
          .count(limit)
          .execute();

      return response.getItems()
          .stream()
          .map(vkU -> new University(vkU.getId(), vkU.getTitle()))
          .toList();

    } catch (Exception e) {
      throw new VkUniversityLookupServiceException("University VK lookup fetching error", e);
    }
  }

}
