package ru.rshb.intech.digital.service.controller.puzzles;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CrosswordControllerNegativeTest extends AbstractIntegrationTest {

    @Test
    void postWinnerInfo_no_crossword() throws Exception {

        final var endpoint = endpointUri("/puzzle/crossword/eb4ef40c-7f74-452f-8f68-99af21002224/participant");
        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .part(new MockPart(fileMock.getName(), fileMock.getOriginalFilename(), fileMock.getBytes()))
                .param("applicantName", "Иван Иванович Иванов")
                .param("phone", "+7 (977) 989-23-12")
                .param("email", "example@example.com")
                .param("about", "description");

        mockMvc()
                .perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {"status":"ERROR","message":"Кроссворд не найден: eb4ef40c-7f74-452f-8f68-99af21002224"}
                    """
                ));
    }

    @Test
    void postWinnerInfo_no_attachment() throws Exception {

        final var endpoint = endpointUri("/puzzle/crossword/eb4ef40c-7f74-452f-8f68-99af21002224/participant");

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .param("applicantName", "Иван Иванович Иванов")
                .param("phone", "+7 (977) 989-23-12")
                .param("email", "example@example.com")
                .param("about", "description");

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
    void postWinnerInfo_no_phone() throws Exception {

        final var endpoint = endpointUri("/puzzle/crossword/eb4ef40c-7f74-452f-8f68-99af21002224/participant");
        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .param("applicantName", "Иван Иванович Иванов")
                .param("email", "example@example.com")
                .param("about", "description");

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
    void postWinnerInfo_phone_bad_format() throws Exception {

        final var endpoint = endpointUri("/puzzle/crossword/eb4ef40c-7f74-452f-8f68-99af21002224/participant");
        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .param("applicantName", "Иван Иванович Иванов")
                .param("phone", "abracadabra вместо телефона")
                .param("email", "example@example.com")
                .param("about", "description");

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
    void postWinnerInfo_phone_bad_format_length() throws Exception {

        final var endpoint = endpointUri("/puzzle/crossword/eb4ef40c-7f74-452f-8f68-99af21002224/participant");
        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .param("applicantName", "Иван Иванович Иванов")
                .param("phone", "+7")
                .param("email", "example@example.com")
                .param("about", "description");

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
    void postWinnerInfo_phone_bad_format_big_length() throws Exception {

        final var endpoint = endpointUri("/puzzle/crossword/eb4ef40c-7f74-452f-8f68-99af21002224/participant");
        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .param("applicantName", "Иван Иванович Иванов")
                .param("phone", "+7345345345345345345345345345")
                .param("email", "example@example.com")
                .param("about", "description");

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
    void postWinnerInfo_no_email() throws Exception {

        final var endpoint = endpointUri("/puzzle/crossword/eb4ef40c-7f74-452f-8f68-99af21002224/participant");
        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .param("applicantName", "Иван Иванович Иванов")
                .param("phone", "+7 (977) 989-23-12")
                .param("about", "description");

        mockMvc()
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"ERROR","message":"Ошибка валидации","errors":{"email":"Не указан адрес электронной почты"}}
                        """
                ));
    }


    @Test
    void getCrossword() throws Exception {

        final var endpoint = endpointUri("/puzzle/crossword/eb4ef40c-7f74-452f-8f68-99af21002224");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {"status":"ERROR","message":"Кроссворд не найден: eb4ef40c-7f74-452f-8f68-99af21002224"}
                    """
                ));
    }

    @Test
    void getCrosswordsByCategory() throws Exception {

        final var endpoint = endpointUri("/puzzle/crossword?categoryId=abracadabra");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void getAnswer() throws Exception {

        final var endpoint = endpointUri("/puzzle/crossword/question/dc7a966c-0f9c-4366-95ff-28eac4297253/answer");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"ERROR","message":"Вопрос не найден: dc7a966c-0f9c-4366-95ff-28eac4297253"}
                        """
                ));
    }

    @Test
    void postGuess() throws Exception {

        final var endpoint = endpointUri("/puzzle/crossword/question/dc7a966c-0f9c-4366-95ff-28eac4297253/guess");
        final var request = MockMvcRequestBuilders.post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {"guess": "anyword"}
                        """
                );

        mockMvc()
                .perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"ERROR","message":"Вопрос не найден: dc7a966c-0f9c-4366-95ff-28eac4297253"}
                        """
                ));
    }
}