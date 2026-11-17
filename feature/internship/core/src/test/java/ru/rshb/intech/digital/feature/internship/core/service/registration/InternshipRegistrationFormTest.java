package ru.rshb.intech.digital.feature.internship.core.service.registration;

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
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.repository.education.EducationStageRepository;
import ru.rshb.intech.digital.feature.internship.core.repository.fields.InternshipFieldRepository;
import ru.rshb.intech.digital.feature.internship.core.repository.registration.RegistrationRepository;
import ru.rshb.intech.digital.feature.internship.core.service.hours.WorkHoursService;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.About;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.AboutExperience;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.AboutPortfolioExamples;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.AboutSkills;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.AboutWishedTasks;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.ApplicantName;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.BirthDate;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.City;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.EducationStage;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.Email;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.InternshipFields;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.Phone;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.Telegram;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.University;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.WorkHours;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.references.cities.core.repository.CityRepository;
import ru.rshb.intech.digital.references.universities.core.repository.UniversityRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class InternshipRegistrationFormTest {

  @Mock
  private FormAttributeProvider formAttributeProvider;

  @Mock
  private RegistrationService registrationService;

  @Mock
  private InternshipRegistrationNotification registrationNotification;

  @Mock
  private CensorAnalyzer censorAnalyzer;

  @Mock
  private CityRepository cityRepository;

  @Mock
  private EducationStageRepository educationStageRepository;

  @Mock
  private InternshipFieldRepository internshipFieldRepository;

  @Mock
  private RegistrationRepository registrationRepository;

  @Mock
  private UniversityRepository universityRepository;

  @Mock
  private WorkHoursService workHoursService;

  private InternshipRegistrationForm registrationForm;

  private InternshipRegistrationDto dto;
  private Registration entity;

  @BeforeEach
  void setUp() {

    final var attributes = new HashMap<String, Attribute<InternshipRegistrationDto, ?, Registration, ?>>();
    attributes.put("internshipRegistrationAbout", new About());
    attributes.put("internshipRegistrationAboutExperience", new AboutExperience());
    attributes.put("internshipRegistrationAboutPortfolioExamples", new AboutPortfolioExamples());
    attributes.put("internshipRegistrationAboutSkills", new AboutSkills());
    attributes.put("internshipRegistrationAboutWishedTasks", new AboutWishedTasks());
    attributes.put("internshipRegistrationApplicantName", new ApplicantName());
    attributes.put("internshipRegistrationBirthDate", new BirthDate());
    attributes.put("internshipRegistrationCity", new City(cityRepository, censorAnalyzer));
    attributes.put("internshipRegistrationEducationStage", new EducationStage(educationStageRepository));
    attributes.put("internshipRegistrationEmail", new Email());
    attributes.put("internshipRegistrationFields", new InternshipFields(internshipFieldRepository));
    attributes.put("internshipRegistrationPhone", new Phone(registrationRepository));
    attributes.put("internshipRegistrationTelegram", new Telegram());
    attributes.put("internshipRegistrationUniversity", new University(universityRepository, censorAnalyzer));
    attributes.put("internshipRegistrationWorkHours", new WorkHours(workHoursService));

    given(
        formAttributeProvider.<InternshipRegistrationDto, Registration>findByFormClass(any())
    ).willReturn(
        new ArrayList<>(attributes.values())
    );

    registrationForm = new InternshipRegistrationForm(
        formAttributeProvider,
        censorAnalyzer,
        registrationService,
        registrationNotification
    );

    dto = new InternshipRegistrationDto();
    entity = new Registration();
  }

  @Test
  void getName() {

    final String formName = registrationForm.getName();

    assertThat(formName)
        .isNotNull()
        .isEqualTo(I18n.of(InternshipRegistrationForm.TITLE_KEY));
  }

  @Test
  void givenValid_process_thenSuccess() throws IOException {

    dto.setAttachData(
        new MockMultipartFile(
            "attachFile",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        )
    );
    dto.setPhone("+7 (999) 666-42-42");
    dto.setBirthDate("01.01.1995");
    dto.setEmail("test@test.te");
    dto.setPrefHrsWk("20 часов");
    dto.setInternshipField("Разработка");
    dto.setAboutSkills("Lorem ipsum skills");
    dto.setApplicantName("Иванов Иван Иванович");
    dto.setAboutWishedTasks("Lorem ipsum wished");

    given(censorAnalyzer.analyze(any()))
        .willReturn(PreCheckResult.OK);

    given(internshipFieldRepository.existsAllByTitleIsIn(anySet()))
        .willReturn(true);

    final var savedRegistration = entity.clone();
    savedRegistration.setId(1L);

    given(
        registrationService.save(any())
    ).willReturn(savedRegistration);

    given(
        registrationService.getFormAttachmentAllowedExtensions()
    ).willReturn(new String[]{"pdf"});

    given(
        registrationService.getSkillPassportAllowedExtensions()
    ).willReturn(new String[]{"pdf"});

    final Registration processedReg = registrationForm.process(dto);

    assertThat(processedReg)
        .isNotNull();
  }

  @Test
  void givenSuspicious_process_thenSuccess() {
    dto.setAttachData(
        new MockMultipartFile(
            "attachFile",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        )
    );
    dto.setPhone("+7 (999) 666-42-42");
    dto.setBirthDate("01.01.1995");
    dto.setEmail("test@test.te");
    dto.setPrefHrsWk("20 часов");
    dto.setInternshipField("Разработка");
    dto.setAboutSkills("Lorem ipsum skills");
    dto.setApplicantName("Иванов Иван Иванович");
    dto.setAboutWishedTasks("Lorem ipsum wished");

    given(censorAnalyzer.analyze(any()))
        .will(invocation -> "Иванов Иван Иванович".equals(invocation.getArgument(0))
            ? PreCheckResult.SUSPICIOUS
            : PreCheckResult.OK);

    given(internshipFieldRepository.existsAllByTitleIsIn(anySet()))
        .willReturn(true);

    final var savedRegistration = entity.clone();
    savedRegistration.setId(1L);

    given(
        registrationService.save(any())
    ).willReturn(savedRegistration);

    given(
        registrationService.getFormAttachmentAllowedExtensions()
    ).willReturn(new String[]{"pdf"});

    given(
        registrationService.getSkillPassportAllowedExtensions()
    ).willReturn(new String[]{"pdf"});

    final Registration processedReg = registrationForm.process(dto);

    assertThat(processedReg)
        .isNotNull();
  }
}
