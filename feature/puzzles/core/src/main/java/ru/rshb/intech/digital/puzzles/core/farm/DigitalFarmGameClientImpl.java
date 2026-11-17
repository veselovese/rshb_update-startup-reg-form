package ru.rshb.intech.digital.puzzles.core.farm;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.rshb.intech.digital.puzzles.dto.farm.client.CommonUsageStatsClientDto;

import java.util.Optional;

@Component
public class DigitalFarmGameClientImpl implements DigitalFarmGameClient {

  private static final String STATS_ENDPOINT = "/api/v1/stats";

  private final RestTemplate restTemplate;
  private final String digitalFarmBaseUrl;


  public DigitalFarmGameClientImpl(@Nonnull RestTemplate restTemplate,
                                   @Nonnull @Value("${integrations.digital-farm.base-url}") String digitalFarmBaseUrl) {
    this.restTemplate = restTemplate;
    this.digitalFarmBaseUrl = digitalFarmBaseUrl;
  }


  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public CommonUsageStatsClientDto getCommonUsageStats() {
    try {
      final var url = digitalFarmBaseUrl + STATS_ENDPOINT;
      return Optional.ofNullable(
          restTemplate.getForObject(url, CommonUsageStatsClientDto.class)
      ).orElseThrow(DigitalFarmException::emptyBodyReceived);
    } catch (Exception e) {
      throw DigitalFarmException.cantReceiveData(e);
    }
  }
}
