package ru.rshb.intech.digital.feature.contests.patent.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    schema = "digital_contests",
    name = "patent_fish_fin_round_ref"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FinancingRoundOption extends AbstractLookupEntry {

}
