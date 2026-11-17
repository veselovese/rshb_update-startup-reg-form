package ru.rshb.intech.digital.feature.innovations.core.service.window.proposal;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowProposal;
import ru.rshb.intech.digital.feature.innovations.core.repository.window.WindowProposalRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class WindowProposalServiceImpl implements WindowProposalService {

  private static final String[] ATTACHMENT_ALLOWED_EXTENSIONS = {"ppt", "pptx", "doc", "docx", "pdf"};

  private final WindowProposalRepository proposalRepository;


  @Nonnull
  @Override
  @Transactional
  public InnovationWindowProposal save(@Nonnull InnovationWindowProposal proposal) {

    proposal.setExternalId(UUID.randomUUID().toString());
    proposal.setSubmitDate(LocalDateTime.now());

    final var savedProposal = proposalRepository.save(proposal);
    log.info("New innovation proposal has been saved");

    return savedProposal;
  }

  @Nonnull
  @Override
  public String[] getFormAttachmentAllowedExtensions() {
    return ATTACHMENT_ALLOWED_EXTENSIONS;
  }
}
