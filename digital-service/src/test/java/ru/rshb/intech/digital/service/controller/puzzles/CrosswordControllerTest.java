package ru.rshb.intech.digital.service.controller.puzzles;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.content.model.Crossword.Question.Direction;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordRepository;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.QuestionRepository;
import ru.rshb.intech.digital.puzzles.entity.Crossword;
import ru.rshb.intech.digital.puzzles.entity.CrosswordCategory;
import ru.rshb.intech.digital.puzzles.entity.Question;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CrosswordControllerTest extends AbstractIntegrationTest {

    @MockBean
    private CrosswordRepository crosswordRepository;

    @MockBean
    private QuestionRepository questionRepository;


    @Test
    void postWinnerInfo() throws Exception {

        when(crosswordRepository.existsByExternalId("eb4ef40c-7f74-452f-8f68-99af21002224"))
                .thenReturn(true);

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
    void getCrossword() throws Exception {

        final var category = new CrosswordCategory();
        category.setId(1L);
        category.setExternalId("37bc1fec-4c40-411e-befe-1da4e9d261cd");
        category.setTitle("Разработка");

        final var question = new Question();
        question.setId(1L);
        question.setExternalId("dc7a966c-0f9c-4366-95ff-28eac4297253");
        question.setTitle("Question text");
        question.setAnswer("answer");
        question.setDirection(Direction.ROW);
        question.setRow(1L);
        question.setColumn(2L);

        final var crossword = new Crossword();
        crossword.setId(1L);
        crossword.setExternalId("eb4ef40c-7f74-452f-8f68-99af21002224");
        crossword.setDeleted(false);
        crossword.setCategories(Set.of(category));
        crossword.setQuestions(Set.of(question));

        when(crosswordRepository.findByExternalId("eb4ef40c-7f74-452f-8f68-99af21002224"))
                .thenReturn(Optional.of(crossword));

        final var endpoint = endpointUri("/puzzle/crossword/eb4ef40c-7f74-452f-8f68-99af21002224");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {
                        "id": "eb4ef40c-7f74-452f-8f68-99af21002224",
                        "categories": [
                            {
                                "id": "37bc1fec-4c40-411e-befe-1da4e9d261cd",
                                "title": "Разработка"
                            }
                        ],
                        "crossword": [
                            {
                                "id": "dc7a966c-0f9c-4366-95ff-28eac4297253",
                                "title": "Question text",
                                "start": [2, 1],
                                "count": 6,
                                "direction": "row"
                            }
                        ]
                    }
                    """
                ));
    }

    @Test
    void getCategories() throws Exception {

        final var endpoint = endpointUri("/puzzle/crossword/categories");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    [
                        {
                            "id": "37bc1fec-4c40-411e-befe-1da4e9d261cd",
                            "title": "Разработка"
                        },
                        {
                            "id": "8fcb326e-616c-4342-83c7-2ec9d2cfa963",
                            "title": "Дизайн"
                        },
                        {
                            "id": "3a25bccb-fca2-4290-9e8b-45ada78eda7c",
                            "title": "Менеджмент"
                        },
                        {
                            "id": "ad2e1b4f-e003-4d38-bbe0-1d9c86226858",
                            "title": "ИИ"
                        },
                        {
                            "id": "1fe359ab-3a3e-4f3f-a393-a816835b50be",
                            "title": "Агротех"
                        },
                        {
                            "id": "c5f9b711-1c4e-435f-9b24-1e64f70726ee",
                            "title": "Финтех"
                        },
                        {
                            "id": "6f31f949-0397-4a06-abe2-bdd6c75d1dec",
                            "title": "ИТ"
                        }
                    ]
                    """
                ));
    }

    @Test
    void getAnswer() throws Exception {

        final var question = new Question();
        question.setId(1L);
        question.setExternalId("dc7a966c-0f9c-4366-95ff-28eac4297253");
        question.setTitle("Question text");
        question.setAnswer("anyword");
        question.setDirection(Direction.ROW);
        question.setRow(1L);
        question.setColumn(2L);

        when(questionRepository.findByExternalId("dc7a966c-0f9c-4366-95ff-28eac4297253"))
                .thenReturn(Optional.of(question));

        final var endpoint = endpointUri("/puzzle/crossword/question/dc7a966c-0f9c-4366-95ff-28eac4297253/answer");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {"answer": "anyword"}
                    """
                ));
    }

    @Test
    void postGuess() throws Exception {

        final var question = new Question();
        question.setId(1L);
        question.setExternalId("dc7a966c-0f9c-4366-95ff-28eac4297253");
        question.setTitle("Question text");
        question.setAnswer("anyword");
        question.setDirection(Direction.ROW);
        question.setRow(1L);
        question.setColumn(2L);

        when(questionRepository.findByExternalId("dc7a966c-0f9c-4366-95ff-28eac4297253"))
                .thenReturn(Optional.of(question));

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
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    """
                    {"right": true}
                    """
                ));
    }
}