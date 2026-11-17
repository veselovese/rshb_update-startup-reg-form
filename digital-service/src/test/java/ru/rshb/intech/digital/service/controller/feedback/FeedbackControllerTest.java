package ru.rshb.intech.digital.service.controller.feedback;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FeedbackControllerTest extends AbstractIntegrationTest {

    @Test
    void postFeedback() throws Exception {

        final var fileMock = new MockMultipartFile(
                "attachment",
                "feedback.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "Повар сказал повару...".getBytes(StandardCharsets.UTF_8)
        );

        final var endpoint = endpointUri("/feedback");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иван")
                .param("phone", "+7 (999) 666-34-34")
                .param("email", "none@none.com")
                .param("feedback", "Lorem ipsum");

        mockMvc()
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {
                        "status": "SUCCESS",
                        "message": "Обратная связь принята"
                    }
                    """
                ));
    }

    @Test
    void postFeedback_only_necessary_fields() throws Exception {

        final var endpoint = endpointUri("/feedback");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("email", "none@none.com")
                .param("feedback", "Lorem ipsum");

        mockMvc()
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {
                            "status": "SUCCESS",
                            "message": "Обратная связь принята"
                        }
                        """
                ));
    }
}