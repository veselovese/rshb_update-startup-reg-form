package ru.rshb.intech.digital.service.controller.contests.chef;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ChefContestControllerNegativeTest extends AbstractIntegrationTest {

    @Test
    void postUrlCheck() throws Exception {

        final var endpoint = endpointUri("/contest/chef/url");

        final var request = MockMvcRequestBuilders.post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {"url": "http://localhost1:8080/find-best-farmer"}
                        """
                );

        mockMvc()
                .perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {"status":"ERROR","message":"Ресурс недоступен по данному URL"}
                    """
                ));
    }

    @Test
    void postUrlCheck_url_bad_format() throws Exception {

        final var endpoint = endpointUri("/contest/chef/url");

        final var request = MockMvcRequestBuilders.post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {"url": "что-то отдаленно напоминающее URL"}
                        """
                );

        mockMvc()
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"ERROR","message":"Некорректный формат URL"}
                        """
                ));
    }

    @Test
    void postContestantForm_no_attachment() throws Exception {

        final var endpoint = endpointUri("/contest/chef/contestant");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("url", "http://localhost:8080/mock/chef/5/find-best-farmer")
                .param("level", "5")
                .param("contestantName", "Василий Иванович Нехочуха")
                .param("phone", "+7 (999) 666-12-32");

        mockMvc()
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {"status":"ERROR","message":"Не указан файл"}
                    """
                ));
    }

    @Test
    void postContestantForm_no_url() throws Exception {

        final var fileMock = new MockMultipartFile(
                "attachment",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Повар сказал повару...".getBytes(StandardCharsets.UTF_8)
        );

        final var endpoint = endpointUri("/contest/chef/contestant");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("level", "5")
                .param("contestantName", "Василий Иванович Нехочуха")
                .param("phone", "+7 (999) 666-12-32");

        mockMvc()
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"ERROR","message":"Ошибка валидации","errors":{"url":"Не указан URL решения"}}
                        """
                ));
    }

    @Test
    void postContestantForm_no_level() throws Exception {

        final var fileMock = new MockMultipartFile(
                "attachment",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Повар сказал повару...".getBytes(StandardCharsets.UTF_8)
        );

        final var endpoint = endpointUri("/contest/chef/contestant");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("url", "http://localhost:8080/mock/chef/5/find-best-farmer")
                .param("contestantName", "Василий Иванович Нехочуха")
                .param("phone", "+7 (999) 666-12-32");

        mockMvc()
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"ERROR","message":"Ошибка валидации","errors":{"level":"Не указано кол-во правильных ответов"}}
                        """
                ));
    }

    @Test
    void postContestantForm_no_contestant_name() throws Exception {

        final var fileMock = new MockMultipartFile(
                "attachment",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Повар сказал повару...".getBytes(StandardCharsets.UTF_8)
        );

        final var endpoint = endpointUri("/contest/chef/contestant");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("url", "http://localhost:8080/mock/chef/5/find-best-farmer")
                .param("level", "5")
                .param("phone", "+7 (999) 666-12-32");

        mockMvc()
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"ERROR","message":"Ошибка валидации","errors":{"contestantName":"Не указано имя участника"}}
                        """
                ));
    }

    @Test
    void postContestantForm_no_phone() throws Exception {

        final var fileMock = new MockMultipartFile(
                "attachment",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Повар сказал повару...".getBytes(StandardCharsets.UTF_8)
        );

        final var endpoint = endpointUri("/contest/chef/contestant");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("url", "http://localhost:8080/mock/chef/5/find-best-farmer")
                .param("level", "5")
                .param("contestantName", "Василий Иванович Нехочуха");

        mockMvc()
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"ERROR","message":"Ошибка валидации","errors":{"phone":"Не указан контактный номер телефона"}}
                        """
                ));
    }

    @Test
    void postContestantForm_invalid_phone() throws Exception {

        final var fileMock = new MockMultipartFile(
                "attachment",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Повар сказал повару...".getBytes(StandardCharsets.UTF_8)
        );

        final var endpoint = endpointUri("/contest/chef/contestant");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("url", "http://localhost:8080/mock/chef/5/find-best-farmer")
                .param("level", "5")
                .param("contestantName", "Василий Иванович Нехочуха")
                .param("phone", "abracadabra");

        mockMvc()
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {
                            "status": "ERROR",
                            "message": "Ошибка валидации",
                            "errors": {
                                "phone": "Некорректный формат номера телефона"
                            }
                        }
                        """
                ));
    }

    @Test
    void postContestantForm_invalid_phone_length() throws Exception {

        final var fileMock = new MockMultipartFile(
                "attachment",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Повар сказал повару...".getBytes(StandardCharsets.UTF_8)
        );

        final var endpoint = endpointUri("/contest/chef/contestant");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("url", "http://localhost:8080/mock/chef/5/find-best-farmer")
                .param("level", "5")
                .param("contestantName", "Василий Иванович Нехочуха")
                .param("phone", "+7");

        mockMvc()
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {
                            "status": "ERROR",
                            "message": "Ошибка валидации",
                            "errors": {
                                "phone": "Некорректный формат номера телефона"
                            }
                        }
                        """
                ));
    }
}