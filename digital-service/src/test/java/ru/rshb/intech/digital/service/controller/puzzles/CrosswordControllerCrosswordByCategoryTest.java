package ru.rshb.intech.digital.service.controller.puzzles;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rshb.intech.digital.content.model.Crossword.Question.Direction;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordCategoryRepository;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordRepository;
import ru.rshb.intech.digital.puzzles.entity.Crossword;
import ru.rshb.intech.digital.puzzles.entity.CrosswordCategory;
import ru.rshb.intech.digital.puzzles.entity.Question;
import ru.rshb.intech.digital.service.test.AbstractIntegrationTest;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CrosswordControllerCrosswordByCategoryTest extends AbstractIntegrationTest {

    @MockBean
    private CrosswordRepository crosswordRepository;

    @MockBean
    private CrosswordCategoryRepository crosswordCategoryRepository;


    @Test
    void getCrosswordsByCategory_without_questions() throws Exception {

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

        category.setCrosswords(Set.of(crossword));

        when(crosswordRepository.findByExternalId("eb4ef40c-7f74-452f-8f68-99af21002224"))
                .thenReturn(Optional.of(crossword));

        when(crosswordCategoryRepository.findByExternalId("37bc1fec-4c40-411e-befe-1da4e9d261cd"))
                .thenReturn(Optional.of(category));


        final var endpoint = endpointUri("/puzzle/crossword?categoryId=37bc1fec-4c40-411e-befe-1da4e9d261cd");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        [
                            {
                                "id": "eb4ef40c-7f74-452f-8f68-99af21002224",
                                "categories": [
                                    {
                                        "id": "37bc1fec-4c40-411e-befe-1da4e9d261cd",
                                        "title": "Разработка"
                                    }
                                ]
                            }
                        ]
                        """
                ));
    }

    @Test
    void getCrosswordsByCategory_with_questions() throws Exception {

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

        category.setCrosswords(Set.of(crossword));

        when(crosswordRepository.findByExternalId("eb4ef40c-7f74-452f-8f68-99af21002224"))
                .thenReturn(Optional.of(crossword));

        when(crosswordCategoryRepository.findByExternalId("37bc1fec-4c40-411e-befe-1da4e9d261cd"))
                .thenReturn(Optional.of(category));


        final var endpoint = endpointUri("/puzzle/crossword?categoryId=37bc1fec-4c40-411e-befe-1da4e9d261cd&question=true");
        final var request = MockMvcRequestBuilders.get(endpoint);

        mockMvc()
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                        [
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
                                        "start": [
                                            2,
                                            1
                                        ],
                                        "count": 6,
                                        "direction": "row"
                                    }
                                ]
                            }
                        ]
                        """
                ));
    }
}