package ru.rshb.intech.digital.feature.internship.core.service.feedback.form;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackLookupsDto;
import ru.rshb.intech.digital.feature.internship.core.dto.LookupItem;
import ru.rshb.intech.digital.feature.internship.core.repository.feedback.InternshipFeedbackRecommendSrcRepository;

@Service
@RequiredArgsConstructor
public class InternshipFeedbackLookupServiceImpl implements InternshipFeedbackLookupService {

  private final InternshipFeedbackRecommendSrcRepository recommendSrcRepository;

  @Nonnull
  @Override
  public InternshipFeedbackLookupsDto getFeedbackFormLookups() {
    return new InternshipFeedbackLookupsDto(
        recommendSrcRepository.findAllByDeletedIsFalse()
            .stream()
            .map(entity -> new LookupItem(entity.getExternalId(), entity.getTitle()))
            .toList()
    );
  }
}
