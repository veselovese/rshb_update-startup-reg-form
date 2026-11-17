package ru.rshb.intech.digital.feature.startups.core.service.registration;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.startups.core.repository.StartupCategoryRepository;
import ru.rshb.intech.digital.feature.startups.core.repository.StartupClientsNumberRepository;
import ru.rshb.intech.digital.feature.startups.core.repository.StartupCountryRepository;
import ru.rshb.intech.digital.feature.startups.core.repository.StartupCurrencyRepository;
import ru.rshb.intech.digital.feature.startups.core.repository.StartupFinancingRoundRepository;
import ru.rshb.intech.digital.feature.startups.core.repository.StartupProjectStageRepository;
import ru.rshb.intech.digital.feature.startups.dto.LookupValueDto;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegLookupDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupCategory;
import ru.rshb.intech.digital.feature.startups.entity.StartupClientsNumberVariant;
import ru.rshb.intech.digital.feature.startups.entity.StartupCurrency;
import ru.rshb.intech.digital.feature.startups.entity.StartupFinancingRound;
import ru.rshb.intech.digital.feature.startups.entity.StartupProjectStage;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StartupRegistrationLookupServiceImpl implements StartupRegistrationLookupService {

  private final StartupCategoryRepository categoryRepository;
  private final StartupProjectStageRepository projectStageRepository;
  private final StartupClientsNumberRepository clientsNumberRepository;
  private final StartupFinancingRoundRepository financingRoundRepository;
  private final StartupCurrencyRepository currencyRepository;
  private final StartupCountryRepository countryRepository;


  @Nonnull
  @Override
  public StartupRegLookupDto getRegistrationLookups() {
    return new StartupRegLookupDto(
        startupCategories(),
        projectStages(),
        clientNumberVariants(),
        financingRounds(),
        currencyTypes(),
        initialCountries()
    );
  }

  @Override
  public boolean isCategoryExist(@Nonnull String catExternalId) {
    return categoryRepository.existsByExternalIdIgnoreCase(catExternalId);
  }

  @Nonnull
  @Override
  public Optional<StartupCategory> findCategory(@Nonnull String externalId) {
    return categoryRepository.findByExternalIdIgnoreCaseAndDisabledIsFalse(externalId);
  }

  @Override
  public boolean isClientsNumberVariantExist(@Nonnull String variantExternalId) {
    return clientsNumberRepository.existsByExternalIdIgnoreCase(variantExternalId);
  }

  @Nonnull
  @Override
  public Optional<StartupClientsNumberVariant> findClientsNumberVariant(@Nonnull String externalId) {
    return clientsNumberRepository.findByExternalIdIgnoreCase(externalId);
  }

  @Override
  public boolean isFinancingRoundExist(@Nonnull String externalId) {
    return financingRoundRepository.existsByExternalIdIgnoreCase(externalId);
  }

  @Nonnull
  @Override
  public Optional<StartupFinancingRound> findFinancingRound(@Nonnull String externalId) {
    return financingRoundRepository.findByExternalIdIgnoreCase(externalId);
  }

  @Override
  public boolean isProjectStageExist(@Nonnull String externalId) {
    return projectStageRepository.existsByExternalIdIgnoreCase(externalId);
  }

  @Nonnull
  @Override
  public Optional<StartupProjectStage> findProjectStage(@Nonnull String externalId) {
    return projectStageRepository.findByExternalIdIgnoreCase(externalId);
  }

  @Override
  public boolean isCurrencyExist(@Nonnull String externalId) {
    return currencyRepository.existsByExternalIdIgnoreCase(externalId);
  }

  @Nonnull
  @Override
  public Optional<StartupCurrency> findCurrency(@Nonnull String externalId) {
    return currencyRepository.findByExternalIdIgnoreCase(externalId);
  }

  @Nonnull
  private List<LookupValueDto> startupCategories() {
    return categoryRepository.findAllByDisabledIsFalse()
        .stream()
        .map(entity -> new LookupValueDto(entity.getExternalId(), entity.getTitle()))
        .toList();
  }

  @Nonnull
  private List<LookupValueDto> projectStages() {
    return projectStageRepository.findAllByDisabledIsFalseOrderById()
        .stream()
        .map(entity -> new LookupValueDto(entity.getExternalId(), entity.getTitle()))
        .toList();
  }

  @Nonnull
  private List<LookupValueDto> clientNumberVariants() {
    return clientsNumberRepository.findAllByDisabledIsFalseOrderById()
        .stream()
        .map(entity -> new LookupValueDto(entity.getExternalId(), entity.getTitle()))
        .toList();
  }

  @Nonnull
  private List<LookupValueDto> financingRounds() {
    return financingRoundRepository.findAllByDisabledIsFalseOrderById()
        .stream()
        .map(entity -> new LookupValueDto(entity.getExternalId(), entity.getTitle()))
        .toList();
  }

  @Nonnull
  private List<LookupValueDto> currencyTypes() {
    return currencyRepository.findAllByDisabledIsFalseOrderById()
        .stream()
        .map(entity -> new LookupValueDto(entity.getExternalId(), entity.getGrapheme()))
        .toList();
  }

  @Nonnull
  private List<LookupValueDto> initialCountries() {
    return countryRepository.findAllByOrderById()
        .stream()
        .map(entity -> new LookupValueDto(entity.getExternalId(), entity.getTitle()))
        .toList();
  }
}
