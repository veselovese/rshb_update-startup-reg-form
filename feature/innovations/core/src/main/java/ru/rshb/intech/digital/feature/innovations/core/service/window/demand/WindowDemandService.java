package ru.rshb.intech.digital.feature.innovations.core.service.window.demand;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowDemand;

import java.util.Map;
import java.util.Set;

public interface WindowDemandService {

  @Nonnull
  InnovationWindowDemand save(@Nonnull InnovationWindowDemand demand);

  @Nonnull
  Map<String, Long> proposalCounts(@Nonnull Set<String> demandIds);

  @Nonnull
  String[] getFormAttachmentAllowedExtensions();
}
