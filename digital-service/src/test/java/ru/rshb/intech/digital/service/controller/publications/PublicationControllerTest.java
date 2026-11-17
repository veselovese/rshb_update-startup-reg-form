package ru.rshb.intech.digital.service.controller.publications;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PublicationControllerTest extends AbstractIntegrationTest {

//    @MockBean
//    private CachedContentService contentService;

    @Test
    void getSummary() throws Exception {

//        final var pub = new Publication();
//        pub.setId("123");
//        pub.setType(PublicationType.ARTICLE);
//        pub.setLikes(1L);
//        pub.setViews(2L);

//        when(contentService.isPublicationExist("123"))
//                .thenReturn(true);
//
//        when(contentService.findPublicationById("123"))
//                .thenReturn(Optional.of(pub));

        final var endpoint = endpointUri("/publication?id=123");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    [
                        {
                            "publicationId": "123",
                            "views": 1,
                            "likes": 0,
                            "state": "unliked"
                        }
                    ]
                    """
                ));
    }

    @Test
    void putLike() throws Exception {

//        final var pub = new Publication();
//        pub.setId("123");
//        pub.setType(PublicationType.ARTICLE);
//        pub.setLikes(1L);
//        pub.setViews(2L);

//        when(contentService.isPublicationExist("123"))
//                .thenReturn(true);
//
//        when(contentService.findPublicationById("123"))
//                .thenReturn(Optional.of(pub));

        final var endpoint = endpointUri("/publication/123/like");
        final var request = MockMvcRequestBuilders.put(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "publicationId": "123",
                        "state": "liked"
                    }
                    """
                );

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {
                        "count": 1,
                        "state": "liked"
                    }
                    """
                ));
    }

    @Test
    void getPodcastSummary() throws Exception {

//        final var podcast = new Podcast();
//        podcast.setId("1234");
//        podcast.setType(PublicationType.PODCAST);
//        podcast.setLikes(0L);
//        podcast.setViews(0L);

//        when(contentService.getPodcastById("1234"))
//                .thenReturn(podcast);

        final var endpoint = endpointUri("/publication/podcast?id=1234");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    [
                        {
                            "publicationId": "1234",
                            "views": 0,
                            "likes": 0,
                            "state": "unliked"
                        }
                    ]
                    """
                ));
    }

    @Test
    void getInnovationDemandSummaries() throws Exception {

//        final var demandCard = new InnovationDemandCard();
//        demandCard.setId("2345");
//        demandCard.setType(PublicationType.INNOVATION_DEMAND_CARD);
//        demandCard.setLikes(0L);
//        demandCard.setViews(0L);

//        when(contentService.findInnovationDemandCardById("2345"))
//                .thenReturn(Optional.of(demandCard));

        final var endpoint = endpointUri("/publication/innovation/demand?id=2345");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        [
                            {
                                "publicationId": "2345",
                                "views": 0,
                                "likes": 0,
                                "state": "unliked"
                            }
                        ]
                        """
                ));
    }

    @Test
    void putPodcastListening() throws Exception {

//        final var podcast = new Podcast();
//        podcast.setId("1234");
//        podcast.setType(PublicationType.PODCAST);
//        podcast.setLikes(0L);
//        podcast.setViews(0L);

//        when(contentService.getPodcastById("1234"))
//                .thenReturn(podcast);

        final var endpoint = endpointUri("/publication/podcast/listening/1234");
        final var request = MockMvcRequestBuilders.put(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {
                        "publicationId": "1234",
                        "views": 1,
                        "likes": 0,
                        "state": "unliked"
                    }
                    """
                ));
    }

    @Test
    void putInnovationView() throws Exception {

        final var demandId = "gwsE_0Omu8kJ9MKuuz5fb2342";

        final var endpoint = endpointUri("/publication/innovation/demand/" + demandId);
        final var request = MockMvcRequestBuilders.put(endpoint);

//        final var demandCard = new InnovationDemandCard();
//        demandCard.setTitle("Test Org Innovation Demand");
//        demandCard.setId(demandId);
//        demandCard.setSlug("test-org-demand");
//        demandCard.setType(PublicationType.INNOVATION_DEMAND_CARD);
//        demandCard.setLikes(0L);
//        demandCard.setViews(0L);

//        when(contentService.findInnovationDemandCardById(eq(demandId)))
//                .thenReturn(Optional.of(demandCard));

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {
                            "publicationId": "gwsE_0Omu8kJ9MKuuz5fb2342",
                            "views": 1,
                            "likes": 0,
                            "state": "unliked"
                        }
                        """
                ));

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {
                            "publicationId": "gwsE_0Omu8kJ9MKuuz5fb2342",
                            "views": 2,
                            "likes": 0,
                            "state": "unliked"
                        }
                        """
                ));
    }
}