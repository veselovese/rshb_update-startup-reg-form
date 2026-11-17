package ru.rshb.intech.digital.feature.innovations.core.repository.window;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowDemand;

public interface WindowDemandRepository extends JpaRepository<InnovationWindowDemand, Long> {
}
