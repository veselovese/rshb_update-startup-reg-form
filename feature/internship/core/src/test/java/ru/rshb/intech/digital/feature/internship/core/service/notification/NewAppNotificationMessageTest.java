package ru.rshb.intech.digital.feature.internship.core.service.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.repository.registration.RegistrationRepository;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.references.cities.core.service.CityReferenceService;
import ru.rshb.intech.digital.references.universities.core.service.UniversityReferenceService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NewAppNotificationMessageTest {

  @Mock
  private InternalNotificationService internalNotificationService;

  @Mock
  private CityReferenceService cityReferenceService;

  @Mock
  private UniversityReferenceService universityReferenceService;

  @Mock
  private RegistrationRepository registrationRepository;

  @InjectMocks
  private NewAppNotificationMessage newAppNotificationMessage;

  private Registration registration;


  @BeforeEach
  void setUp() {
    this.registration = new Registration();
  }

  @Test
  void givenValid_sendWith_thenSuccess() {

    registration.setId(1L);
    registration.setRegistrationDate(LocalDateTime.now());
    registration.setApplicantName("Иванов Иван Иванович");
    registration.setEmail("test@test.te");
    registration.setPhone("+79996664242");
    registration.setInternshipField("Разработка");
    registration.setBirthDate(LocalDate.of(1990, 1, 1));
    registration.setCityId("45.000.000.000.1");
    registration.setUniversityId("0b0e1010-0e0c-130c-1310-1013100d0c13");
    registration.setTelegram("https://t.me/XNadoricheva");
    registration.setEducationStage("специалитет");
    registration.setEducationYear(3);
    registration.setAbout("Lorem ipsum");
    registration.setAboutExperience("Lorem ipsum об опыте работы");
    registration.setAboutPortfolio("Lorem ipsum о портфолио");
    registration.setAboutSkills("Lorem ipsum о навыках");
    registration.setAboutWishedTasks("Lorem ipsum о желаемых задачах");

    final var attachment = Attachment.fromMultipart(
        new MockMultipartFile(
            "attachFile",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        ),
        "pdf");

    given(
        universityReferenceService.findUniversityFullNameByIdOrEmpty("0b0e1010-0e0c-130c-1310-1013100d0c13")
    ).willReturn("Московский Государственный Университет");

    newAppNotificationMessage.sendWith(registration, List.of(attachment));
  }

  @Test
  void givenSuspicious_sendWith_thenSuccess() {

    registration.setId(1L);
    registration.setRegistrationDate(LocalDateTime.now());
    registration.setApplicantName("Иванов Иван Иванович");
    registration.setEmail("test@test.te");
    registration.setPhone("+79996664242");
    registration.setInternshipField("Разработка");
    registration.setBirthDate(LocalDate.of(1990, 1, 1));
    registration.setCityId("45.000.000.000.1");
    registration.setUniversityId("0b0e1010-0e0c-130c-1310-1013100d0c13");
    registration.setTelegram("https://t.me/XNadoricheva");
    registration.setEducationStage("специалитет");
    registration.setEducationYear(3);
    registration.setAbout("Lorem ipsum");
    registration.setAboutExperience("Lorem ipsum об опыте работы");
    registration.setAboutPortfolio("Lorem ipsum о портфолио");
    registration.setAboutSkills("Lorem ipsum о навыках");
    registration.setAboutWishedTasks("Lorem ipsum о желаемых задачах");
    registration.setSuspicious(true);

    final var attachment = Attachment.fromMultipart(
        new MockMultipartFile(
            "attachFile",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        ),
        "pdf");

    given(
        universityReferenceService.findUniversityFullNameByIdOrEmpty("0b0e1010-0e0c-130c-1310-1013100d0c13")
    ).willReturn("Московский Государственный Университет");

    newAppNotificationMessage.sendWith(registration, List.of(attachment));
  }

  @Test
  void givenCustomCity_sendWith_thenSuccess() {

    registration.setId(1L);
    registration.setRegistrationDate(LocalDateTime.now());
    registration.setApplicantName("Иванов Иван Иванович");
    registration.setEmail("test@test.te");
    registration.setPhone("+79996664242");
    registration.setInternshipField("Разработка");
    registration.setBirthDate(LocalDate.of(1990, 1, 1));
    registration.setCityCustom("Квадрицепстаун");
    registration.setUniversityId("0b0e1010-0e0c-130c-1310-1013100d0c13");
    registration.setTelegram("https://t.me/XNadoricheva");
    registration.setEducationStage("специалитет");
    registration.setEducationYear(3);
    registration.setAbout("Lorem ipsum");
    registration.setAboutExperience("Lorem ipsum об опыте работы");
    registration.setAboutPortfolio("Lorem ipsum о портфолио");
    registration.setAboutSkills("Lorem ipsum о навыках");
    registration.setAboutWishedTasks("Lorem ipsum о желаемых задачах");

    final var attachment = Attachment.fromMultipart(
        new MockMultipartFile(
            "attachFile",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        ),
        "pdf");

    given(
        universityReferenceService.findUniversityFullNameByIdOrEmpty("0b0e1010-0e0c-130c-1310-1013100d0c13")
    ).willReturn("Московский Государственный Университет");

    newAppNotificationMessage.sendWith(registration, List.of(attachment));
  }

  @Test
  void givenCustomUniversity_sendWith_thenSuccess() {

    registration.setId(1L);
    registration.setRegistrationDate(LocalDateTime.now());
    registration.setApplicantName("Иванов Иван Иванович");
    registration.setEmail("test@test.te");
    registration.setPhone("+79996664242");
    registration.setInternshipField("Разработка");
    registration.setBirthDate(LocalDate.of(1990, 1, 1));
    registration.setCityId("45.000.000.000.1");
    registration.setUniversityCustom("Квадрицепстаунский Государственный Университет");
    registration.setTelegram("https://t.me/XNadoricheva");
    registration.setEducationStage("специалитет");
    registration.setEducationYear(3);
    registration.setAbout("Lorem ipsum");
    registration.setAboutExperience("Lorem ipsum об опыте работы");
    registration.setAboutPortfolio("Lorem ipsum о портфолио");
    registration.setAboutSkills("Lorem ipsum о навыках");
    registration.setAboutWishedTasks("Lorem ipsum о желаемых задачах");

    final var attachment = Attachment.fromMultipart(
        new MockMultipartFile(
            "attachFile",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        ),
        "pdf");

    newAppNotificationMessage.sendWith(registration, List.of(attachment));
  }
}