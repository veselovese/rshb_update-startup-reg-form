package ru.rshb.intech.digital.feature.contests.chef.service.contestant.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.attributes.provider.FormAttributeProvider;
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestantFormData;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;
import ru.rshb.intech.digital.feature.contests.chef.service.ChefContestResultValidator;
import ru.rshb.intech.digital.feature.contests.chef.service.ChefContestService;
import ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.attribute.ContestantName;
import ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.attribute.Level;
import ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.attribute.Phone;
import ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.attribute.SolutionUrl;
import ru.rshb.intech.digital.feature.contests.chef.service.contestant.notification.ChefContestantNotification;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ChefContestantFormTest {

  @Mock
  private FormAttributeProvider formAttributeProvider;

  @Mock
  private CensorAnalyzer censorAnalyzer;

  @Mock
  private ChefContestService chefContestService;

  @Mock
  private ChefContestantNotification chefContestantNotification;

  @Mock
  private ChefContestResultValidator chefContestResultValidator;

  private ChefContestantForm form;
  private ChefContestantFormData dto;

  @BeforeEach
  void setUp() {

    final List<Attribute<ChefContestantFormData, ?, ChefContestant, ?>> attributes = List.of(
        new Phone(chefContestService),
        new SolutionUrl(chefContestService),
        new Level(chefContestResultValidator),
        new ContestantName()
    );

    given(
        formAttributeProvider.<ChefContestantFormData, ChefContestant>findByFormClass(ChefContestantForm.class)
    ).willReturn(attributes);


    form = new ChefContestantForm(
        formAttributeProvider,
        censorAnalyzer,
        chefContestService,
        chefContestantNotification
    );

    dto = new ChefContestantFormData();
  }

  @Test
  void givenValid_process_thenSuccess() {

    dto.setContestantName("Иванов Иван Иванович");
    dto.setPhone("+7 (999) 666-42-42");
    dto.setUrl("https://google.com");
    dto.setLevel(3);
    dto.setAttachment(
        new MockMultipartFile(
            "attachment",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        )
    );

    given(
        chefContestResultValidator.getCheckLevel(dto.getUrl())
    ).willReturn(dto.getLevel());

    given(
        censorAnalyzer.analyze(anyString())
    ).willReturn(PreCheckResult.OK);

    given(
        chefContestService.save(any())
    ).willReturn(new ChefContestant());

    final ChefContestant result = form.process(dto);

    assertThat(result)
        .isNotNull();
  }
}