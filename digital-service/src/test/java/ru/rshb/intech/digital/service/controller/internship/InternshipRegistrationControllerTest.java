package ru.rshb.intech.digital.service.controller.internship;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("no-rate-limit")
class InternshipRegistrationControllerTest extends AbstractIntegrationTest {

    @Test
    void submitRegistration() throws Exception {

        final var endpoint = endpointUri("/registration");

        final var fileMock = new MockMultipartFile(
                "attachData",
                "cv.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "Некое содержимое некоего резюме".getBytes(StandardCharsets.UTF_8)
        );
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .part(new MockPart(fileMock.getName(), fileMock.getOriginalFilename(), fileMock.getBytes()))
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("phone", "+79996664242")
                .param("email", "none@none.com")
                .param("description", "Lorem ipsum")
                .param("internshipField", "Тестирование");

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {
                        "status": "SUCCESS",
                        "userEmail": "none@none.com"
                    }
                    """
                ));
    }

    @Test
    void getAvailableInternshipFields() throws Exception {

        final var request = MockMvcRequestBuilders.get(
                endpointUri("/registration/fields")
        );

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                [
                    {
                        "key": "development",
                        "title": "Разработка"
                    },
                    {
                        "key": "qa",
                        "title": "Тестирование"
                    },
                    {
                        "key": "devops",
                        "title": "DevOps"
                    },
                    {
                        "key": "data.science",
                        "title": "Data Science"
                    },
                    {
                        "key": "analytics",
                        "title": "Аналитика"
                    },
                    {
                        "key": "information.security",
                        "title": "Информационная безопасность"
                    },
                    {
                        "key": "project.management",
                        "title": "Проектный менеджмент"
                    },
                    {
                        "key": "webui.design",
                        "title": "Дизайн web-интерфейсов"
                    },
                    {
                        "key": "content.management",
                        "title": "Контент-менеджмент"
                    },
                    {
                        "key": "it.architecture",
                        "title": "IT-архитектура"
                    },
                    {
                        "key": "marketing",
                        "title": "Маркетинг"
                    }
                ]
                """
                ));

    }
}