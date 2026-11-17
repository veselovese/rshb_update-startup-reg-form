package ru.rshb.intech.digital.service.controller.feedback;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class FeedbackControllerRateLimitTest extends AbstractIntegrationTest {

    @Test
    void postFeedback_rate_limit() throws Exception {

        sendFeedback();
        sendFeedback();
        sendFeedback();

        final var endpoint = endpointUri("/feedback");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("email", "none@none.com")
                .param("feedback", "Lorem ipsum");

        mockMvc()
                .perform(request)
                .andExpect(status().isTooManyRequests());
    }

    private void sendFeedback() throws Exception {

        final var endpoint = endpointUri("/feedback");
        final var request = MockMvcRequestBuilders.multipart(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("email", "none@none.com")
                .param("feedback", "Lorem ipsum");

        mockMvc()
                .perform(request)
                .andExpect(status().isCreated());
    }
}
