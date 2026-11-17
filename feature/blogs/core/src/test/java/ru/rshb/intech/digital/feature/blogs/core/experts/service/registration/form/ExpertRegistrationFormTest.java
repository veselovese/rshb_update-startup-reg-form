package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form;

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
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.ExpertFieldService;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.ExpertRegistrationService;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute.Comment;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute.Directions;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute.Email;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute.ExpertName;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute.PerformanceUrls;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute.Phone;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute.VkProfileUrl;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.notification.ExpertRegistrationNotification;
import ru.rshb.intech.digital.messages.I18n;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ExpertRegistrationFormTest {

  @Mock
  private FormAttributeProvider formAttributeProvider;

  @Mock
  private CensorAnalyzer censorAnalyzer;

  @Mock
  private ExpertFieldService expertFieldService;

  @Mock
  private ExpertRegistrationService expertRegistrationService;

  @Mock
  private ExpertRegistrationNotification expertRegistrationNotification;

  private ExpertRegistrationForm form;
  private ExpertRegistrationFormData dto;
  private ExpertRegistration entity;

  @BeforeEach
  void setUp() {

    final var attributes = new HashMap<String, Attribute<ExpertRegistrationFormData, ?, ExpertRegistration, ?>>();
    attributes.put("expertRegistrationComment", new Comment());
    attributes.put("expertRegistrationExpertName", new ExpertName());
    attributes.put("expertRegistrationDirections", new Directions(expertFieldService));
    attributes.put("expertRegistrationEmail", new Email());
    attributes.put("expertRegistrationPerformanceUrls", new PerformanceUrls());
    attributes.put("expertRegistrationPhone", new Phone());
    attributes.put("expertRegistrationVkProfileUrl", new VkProfileUrl());

    given(
        formAttributeProvider.<ExpertRegistrationFormData, ExpertRegistration>findByFormClass(any())
    ).willReturn(
        new ArrayList<>(attributes.values())
    );

    form = new ExpertRegistrationForm(
        formAttributeProvider,
        censorAnalyzer,
        expertRegistrationService,
        expertRegistrationNotification
    );

    dto = new ExpertRegistrationFormData();
    entity = new ExpertRegistration();
  }

  @Test
  void getName() {

    final String formName = form.getName();

    assertThat(formName)
        .isNotNull()
        .isEqualTo(I18n.of(ExpertRegistrationForm.TITLE_KEY));
  }

  @Test
  void givenValid_process_thenSuccess() {

    dto.setExpertName("Иван Иванович Иванов");
    dto.setEmail("test@test.te");
    dto.setPhone("+7 (999) 666-42-42");
    dto.setDirections(
        List.of(
            "Технологии",
            "Маркетинг"
        )
    );
    dto.setVkProfileUrl("https://vk.com/abracadabra");
    dto.setPerformanceUrls(
        List.of(
            "https://www.youtube.com/watch?v=TFy_GXXo9mI&pp=ygUK0YXRg9GA0LzQsA%3D%3D",
            "https://www.youtube.com/watch?v=UhoabvL8FZg&pp=ygUK0YXRg9GA0LzQsA%3D%3D"
        )
    );
    dto.setComment("Lorem ipsum dolor amit");
    dto.setAttachment(
        new MockMultipartFile(
            "attachment",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        )
    );

    given(censorAnalyzer.analyze(any()))
        .willReturn(PreCheckResult.OK);

    given(
        expertFieldService.isFieldExist(anyString())
    ).willReturn(true);

    given(
        expertRegistrationService.save(any())
    ).willReturn(entity);

    final ExpertRegistration result = form.process(dto);

    assertThat(result)
        .isNotNull();
  }

  @Test
  void givenSuspicious_process_thenSuccess() {

    dto.setExpertName("Иван Иванович Иванов");
    dto.setEmail("test@test.te");
    dto.setPhone("+7 (999) 666-42-42");
    dto.setDirections(
        List.of(
            "Технологии",
            "Маркетинг"
        )
    );
    dto.setVkProfileUrl("https://vk.com/abracadabra");
    dto.setPerformanceUrls(
        List.of(
            "https://www.youtube.com/watch?v=TFy_GXXo9mI&pp=ygUK0YXRg9GA0LzQsA%3D%3D",
            "https://www.youtube.com/watch?v=UhoabvL8FZg&pp=ygUK0YXRg9GA0LzQsA%3D%3D"
        )
    );
    dto.setComment("Lorem ipsum dolor amit");
    dto.setAttachment(null);

    given(censorAnalyzer.analyze(any()))
        .will(invocation -> dto.getExpertName().equals(invocation.getArgument(0))
            ? PreCheckResult.SUSPICIOUS
            : PreCheckResult.OK);

    given(
        expertFieldService.isFieldExist(anyString())
    ).willReturn(true);

    given(
        expertRegistrationService.save(any())
    ).willReturn(entity);

    final ExpertRegistration result = form.process(dto);

    assertThat(result)
        .isNotNull();
  }
}
