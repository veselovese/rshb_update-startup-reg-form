package ru.rshb.intech.digital.tools.internship.registration.generator;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.repository.registration.RegistrationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.StringUtils.capitalize;

@Slf4j
@Component
@RequiredArgsConstructor
public class InternshipRegistrationGeneratorImpl implements InternshipRegistrationGenerator {

  private final RegistrationRepository registrationRepository;
  private final AtomicLong phoneRef = new AtomicLong(79001001000L);

  @Override
  public void generate() {
    final int[] years = {2022, 2023};

    final var registrations = new LinkedList<Registration>();
    for (int year : years) {
      boolean isCurrentYear = (LocalDateTime.now().getYear() == year);
      final var generatedRegs = generateRegistrations(year, isCurrentYear);
      registrations.addAll(generatedRegs);
    }

    log.info("TOTAL: {}", registrations.size());

    registrationRepository.deleteAll();
    log.info("Internship registrations table wiped out");

    long chunksCount = 0;
    while (true) {

      final var buffer = new ArrayList<Registration>();
      for (int i = 0; i < 100; i++) {
        final var item = registrations.poll();
        if (item == null) {
          break;
        }
        buffer.add(item);
      }

      if (!buffer.isEmpty()) {
        final var stopWatch = StopWatch.createStarted();
        final long savedCount = registrationRepository.saveAllAndFlush(buffer).size();
        log.info("Chunk[{}]: written rows: {} of {}: Duration: {}", ++chunksCount, savedCount, registrations.size(), stopWatch);
      } else {
        break;
      }
    }
  }

  @Nonnull
  private List<Registration> generateRegistrations(int year, boolean zerosAfterCurrentMonth) {

    final var genOptions = generateOptions(zerosAfterCurrentMonth);
    final var registrations = new ArrayList<Registration>();
    final var actualCounts = new HashMap<String, Long>();

    for (final var gOps : genOptions) {

      final var targetAmounts = gOps.getAmounts();
      final var field = gOps.getField();

      for (int i = 0; i < 12; i++) {

        final var count = targetAmounts.get(i);

        for (long j = 0; j < count; j++) {
          final Registration registration = null;//generateRegistration(field, nextDateTime(year, i + 1));
          registrations.add(registration);
        }

        actualCounts.put(field, actualCounts.getOrDefault(field, 0L) + count);
      }
    }
    System.out.println(generateCountsReport(year, actualCounts, registrations.size()));
    return registrations;
  }

  @Nonnull
  private List<GenOps> generateOptions(boolean zeroAfterCurrentMonth) {
    final var options = new ArrayList<GenOps>();
    options.add(new GenOps("DevOps", 0L, 40L, zeroAfterCurrentMonth));
    options.add(new GenOps("Аналитика", 0L, 50L, zeroAfterCurrentMonth));
    options.add(new GenOps("Информационная безопасность", 0L, 10, zeroAfterCurrentMonth));
    options.add(new GenOps("Проектный менеджмент", 0L, 80L, zeroAfterCurrentMonth));
    options.add(new GenOps("Контент-менеджмент", 0L, 50L, zeroAfterCurrentMonth));
    options.add(new GenOps("IT-архитектура", 0L, 20, zeroAfterCurrentMonth));
    options.add(new GenOps("Маркетинг", 0L, 50L, zeroAfterCurrentMonth));
    options.add(new GenOps("Data Science", 0L, 100L, zeroAfterCurrentMonth));
    options.add(new GenOps("Разработка", 0L, 200L, zeroAfterCurrentMonth));
    options.add(new GenOps("Тестирование", 0L, 200L, zeroAfterCurrentMonth));
    options.add(new GenOps("Дизайн web-интерфейсов", 0L, 30L, zeroAfterCurrentMonth));
    return options;
  }

  @Nonnull
  private LocalDateTime nextDateTime(int year, int month) {
    return LocalDateTime.of(year, month, nextInt(1, 28), nextInt(0, 24), nextInt(0, 60), nextInt(0, 60));
  }

  @Nonnull
  private String generateCountsReport(long year, @Nonnull Map<String, Long> actualCounts, long total) {
    final var sb = new StringBuilder("Generated for " + year + " year:\n");
    for (final var entry : actualCounts.entrySet()) {
      sb.append("\t").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
    }
    sb.append("----- TOTAL: ").append(total).append(" -----\n");
    return sb.toString();
  }

  @Getter
  private static class GenOps {
    private final String field;
    private final List<Long> amounts;

    public GenOps(@Nonnull String field, long min, long max, boolean zeroAfterCurrentMonth) {

      this.field = field;
      this.amounts = new ArrayList<>();

      final var currentMonth = LocalDateTime.now().getMonth().getValue() - 1;

      for (int i = 0; i < 12; i++) {
        final var count = (zeroAfterCurrentMonth && i >= currentMonth)
            ? 0L
            : RandomUtils.nextLong(min, max + 1);
        this.amounts.add(count);
      }
    }
  }

//  private Registration generateRegistration(@Nonnull String field, @Nonnull LocalDateTime registrationDate) {
//    return Registration.builder()
//        .registrationDate(registrationDate)
//        .applicantName(generateName(5, 10) + " " + generateName(5, 10) + " " + generateName(5, 20))
//        .internshipField(field)
//        .email(generateEmail())
//        .phone(generatePhoneNumber())
//        .about(null)
//        .log("SUCCESS")
//        .telegram(null)
//        .birthDate(null)
//        .cityId(null)
//        .cityCustom(null)
//        .universityId(null)
//        .universityCustom(null)
//        .educationStage(null)
//        .educationYear(null)
//        .aboutSkills(null)
//        .aboutWishedTasks(null)
//        .aboutPortfolio(null)
//        .aboutExperience(null)
//        .build();
//  }

  @Nonnull
  private String generateName(int min, int max) {
    final String randomStr = randomAlphabetic(min, max).toLowerCase();
    return capitalize(randomStr);
  }

  @Nonnull
  private String generateEmail() {
    final var username = randomAlphabetic(5, 20).toLowerCase();
    final var hostname = randomAlphabetic(5, 20).toLowerCase();
    final var domain = randomAlphabetic(2, 3).toLowerCase();
    return username + "@" + hostname + "." + domain;
  }

  @Nonnull
  private String generatePhoneNumber() {
    final var actualValue = phoneRef.incrementAndGet();
    return "+" + actualValue;
  }
}
