package ru.rshb.intech.digital.feature.innovations.core.service.general;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.innovations.core.entity.GeneralRequest;
import ru.rshb.intech.digital.feature.innovations.core.repository.general.GeneralRequestRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeneralRequestServiceImpl implements GeneralRequestService {

  private static final String[] ATTACHMENT_ALLOWED_EXTENSIONS = {"ppt", "pptx", "doc", "docx", "pdf"};

  private final GeneralRequestRepository requestRepository;


  @Nonnull
  @Override
  @Transactional
  public GeneralRequest save(@Nonnull GeneralRequest generalRequest) {
    generalRequest.setSubmitDate(LocalDateTime.now());
    final var savedRequest = requestRepository.save(generalRequest);
    log.info("New innovations general request has been saved");
    return savedRequest;
  }

  @Override
  public boolean isRequestExist(@Nonnull String phone) {
    return requestRepository.existsByPhone(phone);
  }

  @Nonnull
  @Override
  public String[] getFormAttachmentAllowedExtensions() {
    return ATTACHMENT_ALLOWED_EXTENSIONS;
  }
}
