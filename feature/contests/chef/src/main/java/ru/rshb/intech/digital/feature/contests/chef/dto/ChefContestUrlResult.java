package ru.rshb.intech.digital.feature.contests.chef.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class ChefContestUrlResult {

  @NotNull
  private Long farmerId;

  @NotNull
  private List<Long> relevantProductIds;
}
