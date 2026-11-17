package ru.rshb.intech.digital.feature.innovations.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_innovations",
    name = "o_plt_window_proposals"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class InnovationWindowProposal {

  public static final int DEMAND_CARD_ID_LENGTH = 100;
  public static final int DESCRIPTION_LENGTH = 1000;
  public static final int STARTUP_LINK_LENGTH = 500;
  public static final int STARTUP_NAME_LENGTH = 200;
  public static final int REPRESENTATIVE_LENGTH = 200;
  public static final int EMAIL_LENGTH = 254;
  public static final int PHONE_LENGTH = 15;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      nullable = false,
      unique = true
  )
  private String externalId;

  @Column(
      name = "submit_date",
      nullable = false
  )
  private LocalDateTime submitDate;

  @Column(
      name = "demand_card_id",
      length = DEMAND_CARD_ID_LENGTH,
      nullable = false
  )
  private String demandCardId;

  @Column(
      name = "description",
      length = DESCRIPTION_LENGTH,
      nullable = false
  )
  private String description;

  @Column(
      name = "startup_link",
      length = STARTUP_LINK_LENGTH
  )
  private String startupLink;

  @Column(
      name = "startup_name",
      length = STARTUP_NAME_LENGTH
  )
  private String startupName;

  @Column(
      name = "representative_name",
      length = REPRESENTATIVE_LENGTH
  )
  private String representativeName;

  @Column(
      name = "phone",
      nullable = false,
      length = PHONE_LENGTH
  )
  private String phone;

  @Column(
      name = "email",
      nullable = false,
      length = EMAIL_LENGTH
  )
  private String email;

  @Column(
      name = "suspicious"
  )
  private Boolean suspicious;

  public boolean isSuspicious() {
    return (suspicious != null && suspicious);
  }
}
