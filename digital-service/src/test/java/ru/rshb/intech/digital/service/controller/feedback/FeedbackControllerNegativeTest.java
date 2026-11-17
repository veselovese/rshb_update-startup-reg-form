package ru.rshb.intech.digital.service.controller.feedback;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("no-rate-limit")
class FeedbackControllerNegativeTest extends AbstractIntegrationTest {

    @Test
    void postFeedback_phone_bad_format() throws Exception {

        final var endpoint = endpointUri("/feedback");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("phone", "abracadabra")
                .param("email", "none@none.com")
                .param("feedback", "Lorem ipsum");

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
    void postFeedback_phone_too_short() throws Exception {

        final var endpoint = endpointUri("/feedback");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("phone", "+7 ")
                .param("email", "none@none.com")
                .param("feedback", "Lorem ipsum");

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
    void postFeedback_email_missing() throws Exception {

        final var endpoint = endpointUri("/feedback");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("feedback", "Lorem ipsum");

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
                                "email": "Не указан адрес электронной почты"
                            }
                        }
                        """
                ));
    }

    @Test
    void postFeedback_email_bad_format() throws Exception {

        final var endpoint = endpointUri("/feedback");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("email", "abracadabra")
                .param("feedback", "Lorem ipsum");

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
                                "email": "Некорректный формат адреса электронной почты"
                            }
                        }
                        """
                ));
    }

    @Test
    void postFeedback_feedback_missing() throws Exception {

        final var endpoint = endpointUri("/feedback");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("email", "none@none.com");

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
                                "feedback": "Не указан текст отзыва"
                            }
                        }
                        """
                ));
    }
}