package ru.rshb.intech.digital.feature.innovations.core.service.window.proposal;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowProposal;

public interface WindowProposalService {

  @Nonnull
  InnovationWindowProposal save(@Nonnull InnovationWindowProposal proposal);

  @Nonnull
  String[] getFormAttachmentAllowedExtensions();
}
