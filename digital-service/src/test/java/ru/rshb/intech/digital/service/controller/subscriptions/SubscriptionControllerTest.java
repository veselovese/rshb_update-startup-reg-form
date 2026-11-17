package ru.rshb.intech.digital.service.controller.subscriptions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;
import ru.rshb.intech.digital.subscriptions.core.repository.SubscriptionRepository;
import ru.rshb.intech.digital.subscriptions.entity.Subscription;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SubscriptionControllerTest extends AbstractIntegrationTest {

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @Test
    void getSubscriptionCategories() throws Exception {

        final var endpoint = endpointUri("/subscription/categories");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {
                        "news": [
                            {
                                "id": "8ccbd91f-7da1-4248-a286-bc48a9c64414",
                                "title": "Новости банка"
                            },
                            {
                                "id": "7a3964f1-9e08-4ec0-8d2d-36c0a77877be",
                                "title": "Бизнес"
                            },
                            {
                                "id": "017e5896-b2f5-4ff5-b580-a3ad2cfd898d",
                                "title": "Исследования"
                            },
                            {
                                "id": "df32f700-1e6e-49a8-8508-d2cbafc8d85a",
                                "title": "Технологии"
                            },
                            {
                                "id": "5f21d7e1-ffce-49c2-80e8-2311173e1e7c",
                                "title": "Безопасность"
                            }
                        ],
                        "events": [
                            {
                                "id": "6d591f20-b330-4d31-8bbf-e1143f80c322",
                                "title": "Разработка"
                            },
                            {
                                "id": "4cf1b44d-96a6-4336-aaaa-478b1a3a9aea",
                                "title": "DataScience"
                            },
                            {
                                "id": "6169085e-c9f8-40ff-8514-b54610bc051d",
                                "title": "Агротех"
                            },
                            {
                                "id": "e33780b3-2aa4-4fa6-b05b-7192ac163420",
                                "title": "Стартапы"
                            },
                            {
                                "id": "59088062-8b19-4933-874d-75c4816931dd",
                                "title": "Карьера"
                            }
                        ],
                        "podcasts": [
                            {
                                "id": "e2c5cc66-bf39-48d7-9943-20b0bc3af153",
                                "title": "Цифровая грядка"
                            },
                            {
                                "id": "d0c474bc-19f5-48e4-828d-bec17b77e6cc",
                                "title": "Фермер в деле"
                            },
                            {
                                "id": "a579919c-7371-46af-b981-216111606621",
                                "title": "В десятку!"
                            }
                        ]
                    }
                    """
                ));
    }

    @Test
    void createSubscription() throws Exception {

        final var sub = new Subscription();
        sub.setId(1L);
        sub.setCreationDate(LocalDateTime.now());
        sub.setEmail("none@none.com");
        sub.setActivationCode("a09ab4fa-8a01-4b83-9201-dd8592e71769");

        when(subscriptionRepository.save(any()))
                .thenReturn(sub);

        final var endpoint = endpointUri("/subscription");
        final var request = MockMvcRequestBuilders.put(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                {
                                    "email": "none@none.com",
                                    "categories": {
                                        "popular": false,
                                        "news": [
                                            {
                                                "id": "8ccbd91f-7da1-4248-a286-bc48a9c64414",
                                                "title": "Новости банка"
                                            }
                                        ],
                                        "events": [
                                            {
                                                "id": "59088062-8b19-4933-874d-75c4816931dd",
                                                "title": "Карьера"
                                            }
                                        ],
                                        "podcasts": [
                                        ]
                                    }
                                }
                                """
                        );

        mockMvc()
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {"status":"SUCCESS"}
                    """
                ));
    }

    @Test
    void createSubscription_empty_email() throws Exception {

        final var sub = new Subscription();
        sub.setId(1L);
        sub.setCreationDate(LocalDateTime.now());
        sub.setEmail("none@none.com");
        sub.setActivationCode("a09ab4fa-8a01-4b83-9201-dd8592e71769");

        when(subscriptionRepository.save(any()))
                .thenReturn(sub);

        final var endpoint = endpointUri("/subscription");
        final var request = MockMvcRequestBuilders.put(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {
                            "email": "",
                            "categories": {
                                "popular": false,
                                "news": [
                                    {
                                        "id": "8ccbd91f-7da1-4248-a286-bc48a9c64414",
                                        "title": "Новости банка"
                                    }
                                ],
                                "events": [
                                    {
                                        "id": "59088062-8b19-4933-874d-75c4816931dd",
                                        "title": "Карьера"
                                    }
                                ],
                                "podcasts": [
                                ]
                            }
                        }
                        """
                );

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
    void createSubscription_blank_email() throws Exception {

        final var sub = new Subscription();
        sub.setId(1L);
        sub.setCreationDate(LocalDateTime.now());
        sub.setEmail("none@none.com");
        sub.setActivationCode("a09ab4fa-8a01-4b83-9201-dd8592e71769");

        when(subscriptionRepository.save(any()))
                .thenReturn(sub);

        final var endpoint = endpointUri("/subscription");
        final var request = MockMvcRequestBuilders.put(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {
                            "email": "  ",
                            "categories": {
                                "popular": false,
                                "news": [
                                    {
                                        "id": "8ccbd91f-7da1-4248-a286-bc48a9c64414",
                                        "title": "Новости банка"
                                    }
                                ],
                                "events": [
                                    {
                                        "id": "59088062-8b19-4933-874d-75c4816931dd",
                                        "title": "Карьера"
                                    }
                                ],
                                "podcasts": [
                                ]
                            }
                        }
                        """
                );

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
    void createSubscription_no_email() throws Exception {

        final var sub = new Subscription();
        sub.setId(1L);
        sub.setCreationDate(LocalDateTime.now());
        sub.setEmail("none@none.com");
        sub.setActivationCode("a09ab4fa-8a01-4b83-9201-dd8592e71769");

        when(subscriptionRepository.save(any()))
                .thenReturn(sub);

        final var endpoint = endpointUri("/subscription");
        final var request = MockMvcRequestBuilders.put(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {
                            "categories": {
                                "popular": false,
                                "news": [
                                    {
                                        "id": "8ccbd91f-7da1-4248-a286-bc48a9c64414",
                                        "title": "Новости банка"
                                    }
                                ],
                                "events": [
                                    {
                                        "id": "59088062-8b19-4933-874d-75c4816931dd",
                                        "title": "Карьера"
                                    }
                                ],
                                "podcasts": [
                                ]
                            }
                        }
                        """
                );

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
    void popularSubscription() throws Exception {

        final var sub = new Subscription();
        sub.setId(1L);
        sub.setCreationDate(LocalDateTime.now());
        sub.setEmail("none@none.com");
        sub.setActivationCode("a09ab4fa-8a01-4b83-9201-dd8592e71769");

        when(subscriptionRepository.save(any()))
                .thenReturn(sub);

        final var endpoint = endpointUri("/subscription");
        final var request = MockMvcRequestBuilders.put(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {
                            "email": "none@none.com",
                            "categories": {
                                "popular": true
                            }
                        }
                        """
                );

        mockMvc()
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"SUCCESS"}
                        """
                ));
    }

    @Test
    void popularSubscription_no_cats_no_popular() throws Exception {

        final var sub = new Subscription();
        sub.setId(1L);
        sub.setCreationDate(LocalDateTime.now());
        sub.setEmail("none@none.com");
        sub.setActivationCode("a09ab4fa-8a01-4b83-9201-dd8592e71769");

        when(subscriptionRepository.save(any()))
                .thenReturn(sub);

        final var endpoint = endpointUri("/subscription");
        final var request = MockMvcRequestBuilders.put(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {
                            "email": "none@none.com",
                            "categories": {
                            }
                        }
                        """
                );

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
                                "categories": "Не указаны разделы для подписки"
                            }
                        }
                        """
                ));
    }

    @Test
    void updateSubscription() throws Exception {

        final var existingSub = new Subscription();
        existingSub.setId(1L);
        existingSub.setCreationDate(LocalDateTime.now());
        existingSub.setEmail("none@none.com");
        existingSub.setActivationCode("a09ab4fa-8a01-4b83-9201-dd8592e71769");

        when(subscriptionRepository.findByEmail(eq("none@none.com")))
                .thenReturn(Optional.of(existingSub));

        final var savedSub = new Subscription();
        savedSub.setId(2L);
        savedSub.setCreationDate(LocalDateTime.now());
        savedSub.setEmail("none@none.com");
        savedSub.setActivationCode("139c6e38-0681-49a4-8546-190863fc6c20");

        when(subscriptionRepository.save(any()))
                .thenReturn(savedSub);

        final var endpoint = endpointUri("/subscription");
        final var request = MockMvcRequestBuilders.put(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {
                            "email": "none@none.com",
                            "categories": {
                                "popular": false,
                                "news": [
                                    {
                                        "id": "8ccbd91f-7da1-4248-a286-bc48a9c64414",
                                        "title": "Новости банка"
                                    }
                                ],
                                "events": [
                                    {
                                        "id": "59088062-8b19-4933-874d-75c4816931dd",
                                        "title": "Карьера"
                                    }
                                ],
                                "podcasts": [
                                ]
                            }
                        }
                        """
                );

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"SUCCESS"}
                        """
                ));
    }

    @Test
    void activateSubscription() throws Exception {

        final var sub = new Subscription();
        sub.setId(1L);
        sub.setActivationCode("a09ab4fa-8a01-4b83-9201-dd8592e71769");
        sub.setEmail("none@none.com");

        when(subscriptionRepository.findSubscriptionByActivationCode("a09ab4fa-8a01-4b83-9201-dd8592e71769"))
                .thenReturn(Optional.of(sub));

        when(subscriptionRepository.save(any()))
                .thenReturn(sub);

        final var endpoint = endpointUri("/subscription/activation");
        final var request = MockMvcRequestBuilders.post(endpoint)
                        .param("code", "a09ab4fa-8a01-4b83-9201-dd8592e71769");

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {"status":"SUBSCRIBED"}
                    """
                ));
    }

    @Test
    void unsubscribe() throws Exception {

        final var sub = new Subscription();
        sub.setId(1L);
        sub.setActivationCode("a09ab4fa-8a01-4b83-9201-dd8592e71769");
        sub.setActivationDate(LocalDateTime.now());
        sub.setDeactivationCode("d53642fd-bd1a-43da-97dd-7a7ccbf44364");
        sub.setEmail("none@none.com");

        when(subscriptionRepository.findSubscriptionByDeactivationCode("d53642fd-bd1a-43da-97dd-7a7ccbf44364"))
                .thenReturn(Optional.of(sub));

        when(subscriptionRepository.save(any()))
                .thenReturn(sub);

        final var endpoint = endpointUri("/subscription/deactivation");
        final var request = MockMvcRequestBuilders.post(endpoint)
                        .param("code", "d53642fd-bd1a-43da-97dd-7a7ccbf44364")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "reasons": [],
                                "feedback": ""
                            }
                            """
                        );

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"UNSUBSCRIBED"}
                        """
                ));
    }

    @Test
    void unsubscribe_no_feedback_fields() throws Exception {

        final var sub = new Subscription();
        sub.setId(1L);
        sub.setActivationCode("a09ab4fa-8a01-4b83-9201-dd8592e71769");
        sub.setActivationDate(LocalDateTime.now());
        sub.setDeactivationCode("d53642fd-bd1a-43da-97dd-7a7ccbf44364");
        sub.setEmail("none@none.com");

        when(subscriptionRepository.findSubscriptionByDeactivationCode("d53642fd-bd1a-43da-97dd-7a7ccbf44364"))
                .thenReturn(Optional.of(sub));

        when(subscriptionRepository.save(any()))
                .thenReturn(sub);

        final var endpoint = endpointUri("/subscription/deactivation");
        final var request = MockMvcRequestBuilders.post(endpoint)
                .param("code", "d53642fd-bd1a-43da-97dd-7a7ccbf44364")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                            }
                         """
                );

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {"status":"UNSUBSCRIBED"}
                        """
                ));
    }

    @Test
    void unsubscribe_no_feedback() throws Exception {

        final var sub = new Subscription();
        sub.setId(1L);
        sub.setActivationCode("a09ab4fa-8a01-4b83-9201-dd8592e71769");
        sub.setActivationDate(LocalDateTime.now());
        sub.setDeactivationCode("d53642fd-bd1a-43da-97dd-7a7ccbf44364");
        sub.setEmail("none@none.com");

        when(subscriptionRepository.findSubscriptionByDeactivationCode("d53642fd-bd1a-43da-97dd-7a7ccbf44364"))
                .thenReturn(Optional.of(sub));

        final var endpoint = endpointUri("/subscription/deactivation");
        final var request = MockMvcRequestBuilders.post(endpoint)
                .param("code", "d53642fd-bd1a-43da-97dd-7a7ccbf44364")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc()
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        {
                            "status": "ERROR",
                            "message": "Некорректные параметры запроса"
                        }
                        """
                ));
    }
}