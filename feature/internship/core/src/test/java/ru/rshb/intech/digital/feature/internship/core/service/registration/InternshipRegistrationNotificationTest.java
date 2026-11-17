package ru.rshb.intech.digital.feature.internship.core.service.registration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.notification.AppInReviewNotificationMessage;
import ru.rshb.intech.digital.feature.internship.core.service.notification.NewAppNotificationMessage;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@DisplayName("Registration notification test")
class InternshipRegistrationNotificationTest {

  @Mock
  private NewAppNotificationMessage newAppNotificationMessage;

  @Mock
  private AppInReviewNotificationMessage appInReviewNotificationMessage;

  @InjectMocks
  private InternshipRegistrationNotification internshipRegistrationNotification;

  private Registration registration;
  private Attachment attachment;

  @BeforeEach
  void setUp() {
    registration = new Registration();
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

    attachment = Attachment.fromMultipart(
        new MockMultipartFile(
            "attachFile",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        ),
        "pdf");
  }

  @Test
  @DisplayName("Внутреннее уведомление о новой заявке отправляется -> OK")
  void send() {
    internshipRegistrationNotification.send(registration, List.of(attachment));
  }

  @Test
  @DisplayName("Уведомление заявителю отправляется -> OK")
  void sendApplicantNotification() {
    internshipRegistrationNotification.sendApplicantNotification(registration);
  }
}