package ru.rshb.intech.digital.service.controller.innovations;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.feature.innovations.core.repository.window.WindowProposalRepository;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("no-rate-limit")
public class InnovationsWindowControllerProposalTest extends AbstractIntegrationTest {

//    @MockBean
//    private CachedContentService contentService;

    @MockBean
    private WindowProposalRepository proposalRepository;


    @Test
    void proposalCounts_no_proposals() throws Exception {

        final var demandId = "gwsE_0Omu8kJ9MKuuz5fb2342";

//        final var demandCard = new InnovationDemandCard();
//        demandCard.setTitle("Test Org Innovation Demand");
//        demandCard.setId(demandId);
//        demandCard.setSlug("test-org-demand");
//        demandCard.setType(PublicationType.INNOVATION_DEMAND_CARD);
//        demandCard.setLikes(0L);
//        demandCard.setViews(0L);

//        when(contentService.findInnovationDemandCardById(eq(demandId)))
//                .thenReturn(Optional.of(demandCard));

        final var endpoint = endpointUri("/innovations/window/demand/proposal/count");

        final var request = MockMvcRequestBuilders.get(endpoint)
                .param("demandId", demandId);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {
                            "gwsE_0Omu8kJ9MKuuz5fb2342": 0
                        }
                        """
                ));
    }

    @Test
    void proposalCounts() throws Exception {

        final var demandId = "gwsE_0Omu8kJ9MKuuz5fb2342";

//        final var demandCard = new InnovationDemandCard();
//        demandCard.setTitle("Test Org Innovation Demand");
//        demandCard.setId(demandId);
//        demandCard.setSlug("test-org-demand");
//        demandCard.setType(PublicationType.INNOVATION_DEMAND_CARD);
//        demandCard.setLikes(0L);
//        demandCard.setViews(0L);

//        when(contentService.findInnovationDemandCardById(eq(demandId)))
//                .thenReturn(Optional.of(demandCard));

        when(proposalRepository.countByDemandCardId(eq(demandId)))
                .thenReturn(7L);

        final var endpoint = endpointUri("/innovations/window/demand/proposal/count");

        final var request = MockMvcRequestBuilders.get(endpoint)
                .param("demandId", demandId);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {
                            "gwsE_0Omu8kJ9MKuuz5fb2342": 7
                        }
                        """
                ));
    }
}
