package ru.rshb.intech.digital.vacancies.core.service.candidate;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.vacancies.core.repository.candidate.VacancyCandidateRepository;
import ru.rshb.intech.digital.vacancies.entity.CandidateCv;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyCandidateServiceImpl implements VacancyCandidateService {

  private static final String[] ALLOWED_EXTENSIONS = {"ppt", "pptx", "doc", "docx", "pdf"};

  private final VacancyCandidateRepository candidateRepository;

  @Nonnull
  @Override
  @Transactional
  public CandidateCv save(@Nonnull CandidateCv cv) {
    cv.setSubmitDate(LocalDateTime.now());
    final var savedCv = candidateRepository.save(cv);
    log.info("New vacancy candidate CV has been saved");
    return savedCv;
  }

  @Nonnull
  @Override
  public String[] getFormAttachmentAllowedExtensions() {
    return ALLOWED_EXTENSIONS;
  }
}
