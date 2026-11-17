package ru.rshb.intech.digital.service.controller.contests.chef;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.feature.contests.chef.service.ChefContestResultValidator;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ChefContestControllerNegativeBLTest extends AbstractIntegrationTest {

    @MockBean
    private ChefContestResultValidator chefContestResultValidator;

    @Test
    void postContestantForm_cheating() throws Exception {

        // Решение возвращает "непроходной" результат
        when(chefContestResultValidator.getCheckLevel("http://localhost:8080/mock/chef/2/find-best-farmer"))
                .thenReturn(2);

        final var endpoint = endpointUri("/contest/chef/contestant");

        final var fileMock = new MockMultipartFile(
                "attachment",
                "cv.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "Повар сказал повару...".getBytes(StandardCharsets.UTF_8)
        );

        final String cheatedLevel = "5"; // Пользователь API указывает "проходной" результат.
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .part(new MockPart(fileMock.getName(), fileMock.getOriginalFilename(), fileMock.getBytes()))
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("url", "http://localhost:8080/mock/chef/2/find-best-farmer")
                .param("level", cheatedLevel)
                .param("contestantName", "Василий Иванович Нехочуха")
                .param("phone", "+7 (999) 666-12-32");

        mockMvc()
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"ERROR","message":"Количество очков не совпадает"}
                        """
                ));
    }
}