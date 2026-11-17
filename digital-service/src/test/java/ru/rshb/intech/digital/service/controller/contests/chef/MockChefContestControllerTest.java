package ru.rshb.intech.digital.service.controller.contests.chef;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import java.net.URI;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MockChefContestControllerTest extends AbstractIntegrationTest {

    @Test
    void findRandomBestFarmer() throws Exception {

        final var endpoint = URI.create("/mock/chef/random/find-best-farmer");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void findExpectedBestFarmer() throws Exception {

        final var endpoint = URI.create("/mock/chef/4/find-best-farmer");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}