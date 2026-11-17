package ru.rshb.intech.digital.service.controller.innovations;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class InnovationsControllerTest extends AbstractIntegrationTest {

    @Test
    void postApplicant() throws Exception {

        final var endpoint = endpointUri("/innovations/apply");
        final var fileMock = new MockMultipartFile(
                "attachData",
                "innovation-proposal.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Фантастические инновации".getBytes(StandardCharsets.UTF_8)
        );

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .file(fileMock)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("phone", "+79996664242")
                .param("email", "none@none.com")
                .param("proposal", "Lorem ipsum");

        mockMvc()
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                            """
                            {
                                "status": "SUCCESS",
                                "message": "Заявка принята"
                            }
                            """
                ));
    }

    @Test
    void postApplicant_no_attachment() throws Exception {

        final var endpoint = endpointUri("/innovations/apply");

        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("applicantName", "Иванов Иван Сидорович")
                .param("phone", "+79996664242")
                .param("email", "none@none.com")
                .param("proposal", "Lorem ipsum");

        mockMvc()
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {
                            "status": "SUCCESS",
                            "message": "Заявка принята"
                        }
                        """
                ));
    }
}