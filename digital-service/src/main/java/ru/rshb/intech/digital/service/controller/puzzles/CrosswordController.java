package ru.rshb.intech.digital.service.controller.puzzles;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.puzzles.core.crossword.service.CrosswordSearchOptions;
import ru.rshb.intech.digital.puzzles.core.crossword.service.CrosswordServiceAdapter;
import ru.rshb.intech.digital.puzzles.core.crossword.service.category.CrosswordCategoryService;
import ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.CrosswordParticipantForm;
import ru.rshb.intech.digital.puzzles.core.crossword.service.question.CrosswordQuestionService;
import ru.rshb.intech.digital.puzzles.core.crossword.service.scores.CrosswordScoresApi;
import ru.rshb.intech.digital.puzzles.dto.Answer;
import ru.rshb.intech.digital.puzzles.dto.CrosswordAnswers;
import ru.rshb.intech.digital.puzzles.dto.CrosswordCategoryDto;
import ru.rshb.intech.digital.puzzles.dto.CrosswordCheckResult;
import ru.rshb.intech.digital.puzzles.dto.CrosswordDto;
import ru.rshb.intech.digital.puzzles.dto.CrosswordFullScoresTableDto;
import ru.rshb.intech.digital.puzzles.dto.CrosswordParticipantDto;
import ru.rshb.intech.digital.puzzles.dto.CrosswordScoresTableDto;
import ru.rshb.intech.digital.puzzles.dto.Guess;
import ru.rshb.intech.digital.puzzles.dto.GuessResult;
import ru.rshb.intech.digital.puzzles.dto.WinnerScoresDto;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

import java.util.List;

import static ru.rshb.intech.digtial.commons.dto.OperationResultResponse.localizedSuccess;

@Tag(
    name = "Кроссворды",
    description = "API функционала кроссвордов"
)
@RestController
@CrossOrigin
@RequestMapping(
    path = "${api.prefix}/puzzle/crossword",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class CrosswordController {

  private final CrosswordServiceAdapter crosswordService;
  private final CrosswordScoresApi crosswordScoresService;
  private final CrosswordCategoryService crosswordCategoryService;
  private final CrosswordQuestionService crosswordQuestionService;
  private final CrosswordParticipantForm crosswordParticipantForm;


  @Operation(
      summary = "Получить кроссворд по ID",
      description = "Возвращает данные кроссворда по его ID.",
      parameters = {
          @Parameter(
              name = "crosswordId",
              description = "ID кроссворда."
          ),
          @Parameter(
              name = "SESSION",
              description = "ID сессии пользователя (base64)",
              in = ParameterIn.COOKIE
          ),
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Операция успешно выполнена."
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Крссворд с указанным ID отсутствует.",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          )
      }
  )
  @GetMapping("/{crosswordId}")
  public CrosswordDto getCrossword(@PathVariable String crosswordId, HttpSession session) {
    return crosswordService.byId(crosswordId, session.getId());
  }

  @Operation(
      summary = "Отправить данные разгадавшего кроссворд",
      description = "Принимает данные формы, которую заполнил пользователь, разгадавший кроссворд.",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Заявка создана."
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Данные формы невалидны",
              content = @Content(
                  schema = @Schema(
                      implementation = FormValidationErrorResponse.class
                  )
              )
          )
      },
      parameters = {
          @Parameter(
              name = "crosswordId",
              description = "ID кроссворда"
          )
      },
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Данные формы"
      )
  )
  @PostMapping(
      path = "/{crosswordId}/participant",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public OperationResultResponse postWinnerInfo(@PathVariable String crosswordId,
                                                @Valid @ModelAttribute CrosswordParticipantDto participantDto) {
    participantDto.setCrosswordId(crosswordId);
    crosswordParticipantForm.process(participantDto);
    return localizedSuccess("common.info.message.application.accepted");
  }

  @Operation(
      summary = "Отправить результат отгадывания кроссворда",
      description = "Принимает результат отгадывания кроссворда",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Результат учтен."
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Данные невалидны",
              content = @Content(
                  schema = @Schema(
                      implementation = FormValidationErrorResponse.class
                  )
              )
          )
      },
      parameters = {
          @Parameter(
              name = "crosswordId",
              description = "ID кроссворда"
          )
      },
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Данные формы"
      )
  )
  @PostMapping(
      path = "/{crosswordId}/scores",
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public OperationResultResponse postWinnerScores(@PathVariable String crosswordId,
                                                  @RequestBody @Validated WinnerScoresDto scoresDto,
                                                  HttpSession session) {
    crosswordScoresService.submit(crosswordId, scoresDto, session.getId());
    return localizedSuccess("puzzle.crossword.scores.submit.success");
  }

  @Operation(
      summary = "Получить таблицу рекордов по ID кроссворда",
      description = """
                    Возвращает список результатов отгадывания кроссворда с данным ID,
                    отсортированный по затраченному времени, и разбитый на страницы.
                    """,
      parameters = {
          @Parameter(
              name = "crosswordId",
              description = "ID кроссворда"
          ),
          @Parameter(
              name = "page",
              description = "Номер страницы. 0-based"
          ),
          @Parameter(
              name = "size",
              description = "Требуемое кол-во элементов на страницу"
          )
      }
  )
  @GetMapping(path = "/{crosswordId}/scores")
  public Object getScoresTable(@PathVariable String crosswordId,
                               @RequestParam(required = false, defaultValue = "false") Boolean pagination,
                               @RequestParam(required = false, defaultValue = "0") Integer page,
                               @RequestParam(required = false, defaultValue = "10") Integer size) {
    return (pagination)
        ? crosswordScoresService.getScoresTableByCrosswordId(crosswordId, PageRequest.of(page, size))
        : crosswordScoresService.getScoresTableByCrosswordId(crosswordId);
  }

  @Operation(
      summary = "Получить таблицу рекордов по всем кроссвордам по категориям",
      description = "Возвращает таблицы рекордов по кроссвордам указанной категории." +
          "Если категория не указана - возвращает таблицы рекордов по всем кроссвордам.",
      parameters = {
          @Parameter(
              name = "catId",
              description = "ID категории кроссвордов",
              in = ParameterIn.QUERY
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Операция успешно выполнена."
          )
      }
  )
  @GetMapping(path = "/scores")
  public CrosswordFullScoresTableDto getAllScores(@RequestParam(name = "catId", required = false) String cwCatId) {
    return crosswordScoresService.getScoresTable(cwCatId);
  }

  @Operation(
      summary = "Получить таблицу рекордов по всем кроссвордам для данного пользователя (по сессии).",
      description = "Возвращает таблицу рекордов по всем кроссвордам для данного пользователя (по сессии).",
      parameters = {
          @Parameter(
              name = "SESSION",
              description = "ID сессии пользователя (base64)",
              in = ParameterIn.COOKIE
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Операция успешно выполнена."
          )
      }
  )
  @GetMapping(path = "/scores/user")
  public List<CrosswordScoresTableDto> getUserScores(HttpSession session) {
    return crosswordScoresService.all(session.getId());
  }


  @Operation(
      summary = "Получить кроссворды по ID категории.",
      description = "Возвращает список кроссвордов, относящихся к категории с указанным ID. " +
                    "В случае отсутствия категории с заданным ID - возвращает пустой список.",
      parameters = {
          @Parameter(
              name = "categoryId",
              description = "ID категории требуемых кроссвордов."
          ),
          @Parameter(
              name = "solved",
              description = """
                            Если true - получить только решенные кроссворды данной категории для данного пользователя.
                            Если false - получить только не решенные кроссворды данной категории для данного пользователя.
                            Если параметр не указан - получить все кроссворды данной категории для данного пользователя.
                            """
          ),
          @Parameter(
              name = "question",
              description = "Если true - получить вместе с кроссвордом все его вопросы."
          ),
          @Parameter(
              name = "SESSION",
              description = "ID сессии пользователя (base64)",
              in = ParameterIn.COOKIE
          ),
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Операция успешно выполнена."
          )
      }
  )
  @GetMapping
  public List<CrosswordDto> getCrosswordsByCategory(@RequestParam(required = false) String categoryId,
                                                    @RequestParam(required = false) Boolean solved,
                                                    @RequestParam(required = false) Boolean question,
                                                    HttpSession session) {
    return crosswordService.bySearchOptions(
        session.getId(),
        CrosswordSearchOptions.builder()
            .categoryId(categoryId)
            .sessionId(session.getId())
            .solved(solved)
            .withQuestions(question)
            .build()
    );
  }

  @Operation(
      summary = "Получить все категории кроссвордов.",
      description = "Возвращает список категорий кроссвордов.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Операция успешно выполнена."
          )
      }
  )
  @GetMapping("/categories")
  public List<CrosswordCategoryDto> getCategories() {
    return crosswordCategoryService.getAll();
  }

  @Operation(
      summary = "Получить ответ на вопрос по ID",
      description = "Возвращает правильный ответ на вопрос кроссворда по ID вопроса.",
      parameters = @Parameter(
          name = "questionId",
          description = "ID вопроса."
      ),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Операция успешно выполнена."
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Вопрос с указанным ID отсутствует.",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          )
      }
  )
  @GetMapping("/question/{questionId}/answer")
  public Answer getAnswer(@PathVariable String questionId) {
    return crosswordQuestionService.answer(questionId);
  }

  @Operation(
      summary = "Проверить ответ на вопрос.",
      description = "Проверяет является ли указанный ответ на вопрос с указанным ID правильным.",
      parameters = @Parameter(
          name = "questionId",
          description = "ID вопроса."
      ),
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Вариант ответа для проверки."
      ),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Операция успешно выполнена."
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Вопрос с указанным ID отсутствует.",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          )
      }
  )
  @PostMapping(
      path = "/question/{questionId}/guess",
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public GuessResult postGuess(@PathVariable String questionId, @RequestBody Guess guess) {
    return crosswordQuestionService.isRightAnswer(questionId, guess.getGuess());
  }


  @Operation(
      summary = "Проверить ответы на вопросы кроссворда.",
      description = "Проверяет правильность ответов на вопросы кроссворда.",
      parameters = @Parameter(
          name = "crosswordId",
          description = "ID кроссворда."
      ),
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Ответы на вопросы для проверки"
      ),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Операция успешно выполнена."
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Кроссворд с указанным ID не существует",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          )
      }
  )
  @PostMapping("/{crosswordId}/guesses")
  public CrosswordCheckResult postCrosswordAnswers(@PathVariable String crosswordId,
                                                   @RequestBody CrosswordAnswers answers) {
    return crosswordQuestionService.checkAnswers(crosswordId, answers);
  }
}
