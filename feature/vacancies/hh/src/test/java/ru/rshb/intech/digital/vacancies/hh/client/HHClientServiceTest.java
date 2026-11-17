package ru.rshb.intech.digital.vacancies.hh.client;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.SpecializationDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.VacancyExtendedDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@SpringBootTest(
        classes = {
                RestTemplate.class,
                TestConfig.class
        }
)
class HHClientServiceTest {

    @Autowired
    @Qualifier("testRestTemplate")
    private RestTemplate restTemplate;

    private HHClient hhClient;

    private HHAccount account;

    private HHClientService clientService;


    @BeforeEach
    void setUp() {

        account = new HHAccount();
        account.setId("3131901");
        account.setName("РСХБ-Интех");

        final var clientOptions = new HHClientOptions();
        clientOptions.setUserAgent("RshbDigital/1.0 (trainee.request@yandex.ru)");

        this.hhClient = new HHClient(restTemplate, clientOptions);

        String token = "PL06RH2CU6BVKH6QA9CVIQ7R0GF28M78DCVUUV19ED7ASQ23DGI5OQSILV6MKDI9";

        this.clientService = new HHClientService(hhClient, token);
    }

    @Test
    void auth() {

        final var auth1 = clientService.getAuth();
        assertNotNull(auth1);
        assertNotNull(auth1.getAccessToken());

        final var auth2 = clientService.getAuth();
        assertNotNull(auth2);

        assertEquals(auth1.getAccessToken(), auth2.getAccessToken());
    }

    @Test
    void findVacancies() {
        final var vacancies = clientService.findAllVacancies(List.of(account));
        assertNotNull(vacancies);
    }

    @Test
    void findFullVacancies() {

        final var vacancyItems = clientService.findAllVacancies(List.of(account));

        final var vacancies = new ArrayList<VacancyExtendedDto>();
        final var specializations = new HashMap<String, SpecializationDto>();
        final var employments = new HashMap<String, String>();
        final var experiences = new HashMap<String, String>();
        final var cities = new HashSet<String>();

        for (final var vacancyItem : vacancyItems) {

            final var vacancy = clientService.getDetailedVacancyById(vacancyItem.getId());
            if (vacancy == null) {
                continue;
            }
            vacancies.add(vacancy);

            // Collect specializations
            ListUtils.emptyIfNull(vacancy.getSpecializations())
                    .forEach(spec -> specializations.put(spec.getId(), spec));

            // Employment
            final var employment = vacancy.getEmployment();
            employments.put(employment.getId(), employment.getName());

            // Experience
            final var experience = vacancy.getExperience();
            experiences.put(experience.getId(), experience.getName());

            // City
            final var city = vacancy.getArea().getName();
            cities.add(city);
        }

        assertFalse(vacancies.isEmpty());
    }

    @Test
    void findSpecifications() {
        final var specializations = clientService.getSpecializations()
                .stream()
                .filter(spec -> StringUtils.containsIgnoreCase(spec.getName(), "Информационные технологии"))
                .toList();
        assertNotNull(specializations);
    }
}