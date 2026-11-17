package ru.rshb.intech.digital.service.controller.contests.chef;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nonnull;
import lombok.Data;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestUrlResult;

import java.util.ArrayList;
import java.util.List;

@Tag(
    name = "Мок для тестирования Повара"
)
@RestController
@CrossOrigin
@RequestMapping(
    path = "/mock/chef",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@Profile({"dev-local", "dev-stand", "test"})
public class MockChefContestController {

  @Operation(
      summary = "Получить результат с рандомным числом ответов."
  )
  @GetMapping("/random/find-best-farmer")
  public ChefContestUrlResult findRandomBestFarmer() {

    final int minSize = 1;
    final int maxSize = 5;

    return generateResult(
        RandomUtils.nextInt(minSize, maxSize + 1)
    );
  }

  @Operation(
      summary = "Получить результат с ожидаемым числом ответов",
      parameters = {
          @Parameter(
              name = "expectedLevel",
              description = "Необходимое число ответов."
          )
      }
  )
  @GetMapping(path = "/{expectedLevel}/find-best-farmer")
  public ChefContestUrlResult findExpectedBestFarmer(@PathVariable String expectedLevel) {
    return generateResult(Integer.parseInt(expectedLevel));
  }

  @GetMapping("/bad/find-best-farmer")
  public BadResponseDto getBadResponse() {
    return new BadResponseDto();
  }

  @Nonnull
  private ChefContestUrlResult generateResult(int size) {

    final List<Long> ids = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      ids.add(RandomUtils.nextLong(1, 100));
    }

    return new ChefContestUrlResult(1L, ids);
  }

  @Data
  public static class BadResponseDto {

    private String abracadabra;
  }
}
