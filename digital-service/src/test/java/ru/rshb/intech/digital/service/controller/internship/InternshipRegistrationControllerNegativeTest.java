package ru.rshb.intech.digital.service.controller.internship;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("no-rate-limit")
class InternshipRegistrationControllerNegativeTest extends AbstractIntegrationTest {

    @Test
    void submitRegistration_no_attachment() throws Exception {

        final var endpoint = endpointUri("/registration");

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("phone", "+79996664242")
                .param("email", "none@none.com")
                .param("description", "Lorem ipsum")
                .param("internshipField", "Тестирование");

        mockMvc()
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {
                            "status": "ERROR",
                            "message": "Не указан файл"
                        }
                        """
                ));
    }

    @Test
    void submitRegistration_no_applicant_name() throws Exception {

        final var endpoint = endpointUri("/registration");

        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("phone", "+79996664242")
                .param("email", "none@none.com")
                .param("description", "Lorem ipsum")
                .param("internshipField", "Тестирование");

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
    void submitRegistration_no_email() throws Exception {

        final var endpoint = endpointUri("/registration");

        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("phone", "+79996664242")
//                .param("email", "none@none.com")
                .param("description", "Lorem ipsum")
                .param("internshipField", "Тестирование");

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
    void submitRegistration_no_phone() throws Exception {

        final var endpoint = endpointUri("/registration");

        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("email", "none@none.com")
                .param("description", "Lorem ipsum")
                .param("internshipField", "Тестирование");

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
    void submitRegistration_phone_bad_format() throws Exception {

        final var endpoint = endpointUri("/registration");

        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("phone", "abracadabra")
                .param("email", "none@none.com")
                .param("description", "Lorem ipsum")
                .param("internshipField", "Тестирование");

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
    void submitRegistration_phone_bad_format_length() throws Exception {

        final var endpoint = endpointUri("/registration");

        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("phone", "+7")
                .param("email", "none@none.com")
                .param("description", "Lorem ipsum")
                .param("internshipField", "Тестирование");

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
    void submitRegistration_phone_bad_format_length_sm() throws Exception {

        final var endpoint = endpointUri("/registration");

        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("phone", "+7999666424")
                .param("email", "none@none.com")
                .param("description", "Lorem ipsum")
                .param("internshipField", "Тестирование");

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