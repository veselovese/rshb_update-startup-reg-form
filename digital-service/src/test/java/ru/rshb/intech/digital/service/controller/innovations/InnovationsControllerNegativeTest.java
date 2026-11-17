package ru.rshb.intech.digital.service.controller.innovations;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class InnovationsControllerNegativeTest extends AbstractIntegrationTest {

    @Test
    void postApplicant_no_applicant_name() throws Exception {

        final var endpoint = endpointUri("/innovations/apply");

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("phone", "+79996664242")
                .param("email", "none@none.com")
                .param("proposal", "Lorem ipsum");

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
                                "applicantName": "Имя не должно быть пустым"
                            }
                        }
                        """
                ));
    }

    @Test
    void postApplicant_no_phone() throws Exception {

        final var endpoint = endpointUri("/innovations/apply");

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("email", "none@none.com")
                .param("proposal", "Lorem ipsum");

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
                                "phone": "Не указан контактный номер телефона"
                            }
                        }
                        """
                ));
    }

    @Test
    void postApplicant_phone_bad_format() throws Exception {

        final var endpoint = endpointUri("/innovations/apply");

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("phone", "abracadabra вместо телефона")
                .param("email", "none@none.com")
                .param("proposal", "Lorem ipsum");

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
    void postApplicant_phone_bad_format_length() throws Exception {

        final var endpoint = endpointUri("/innovations/apply");

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("phone", "+7")
                .param("email", "none@none.com")
                .param("proposal", "Lorem ipsum");

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
    void postApplicant_no_email() throws Exception {

        final var endpoint = endpointUri("/innovations/apply");

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("phone", "abracadabra вместо телефона")
                .param("proposal", "Lorem ipsum");

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
    void postApplicant_no_proposal() throws Exception {

        final var endpoint = endpointUri("/innovations/apply");

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("phone", "abracadabra вместо телефона")
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
                                "proposal": "Не указано предложение или запрос"
                            }
                        }
                        """
                ));
    }
}