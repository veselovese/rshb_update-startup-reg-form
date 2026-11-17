package ru.rshb.intech.digital.feature.innovations.core.repository.window;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowProposal;

public interface WindowProposalRepository extends JpaRepository<InnovationWindowProposal, Long> {

  long countByDemandCardId(@Nonnull String demandId);
}
