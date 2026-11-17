package ru.rshb.intech.digital.feature.innovations.core.service.window.demand;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.provider.api.InnovationContentService;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowDemand;
import ru.rshb.intech.digital.feature.innovations.core.repository.window.WindowDemandRepository;
import ru.rshb.intech.digital.feature.innovations.core.repository.window.WindowProposalRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class WindowDemandServiceImpl implements WindowDemandService {

  private static final String[] ATTACHMENT_ALLOWED_EXTENSIONS = {"ppt", "pptx", "doc", "docx", "pdf"};

  private final WindowDemandRepository demandRepository;
  private final InnovationContentService innovationContentService;
  private final WindowProposalRepository proposalRepository;


  @Nonnull
  @Override
  @Transactional
  public InnovationWindowDemand save(@Nonnull InnovationWindowDemand demand) {

    demand.setExternalId(UUID.randomUUID().toString());
    demand.setSubmitDate(LocalDateTime.now());

    final var savedDemand = demandRepository.save(demand);
    log.info("New innovation demand has been saved");

    return savedDemand;
  }

  @Nonnull
  @Override
  @Transactional
  public Map<String, Long> proposalCounts(@Nonnull Set<String> demandIds) {
    final var counts = new HashMap<String, Long>();
    for (var demandId : demandIds) {
      if (!innovationContentService.isExistById(demandId)) {
        counts.put(demandId, 0L);
        continue;
      }
      final long proposalCount = proposalRepository.countByDemandCardId(demandId);
      counts.put(demandId, proposalCount);
    }
    return counts;
  }

  @Nonnull
  @Override
  public String[] getFormAttachmentAllowedExtensions() {
    return ATTACHMENT_ALLOWED_EXTENSIONS;
  }
}
