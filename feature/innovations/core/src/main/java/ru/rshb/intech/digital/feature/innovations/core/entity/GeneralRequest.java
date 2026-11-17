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
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_innovations",
    name = "o_plt_applicants"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class GeneralRequest {

  public static final int APPLICANT_NAME_LENGTH = 300;
  public static final int PROPOSAL_LENGTH = 1000;
  public static final int LOG_LENGTH = 1000;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "applicant_name",
      nullable = false,
      length = APPLICANT_NAME_LENGTH
  )
  private String applicantName;

  @Column(
      name = "phone",
      unique = true,
      length = 15
  )
  private String phone;

  @Column(
      name = "email",
      length = 320
  )
  private String email;

  @Column(
      name = "about_proposal",
      length = 1000
  )
  private String proposal;

  @Column(
      name = "submit_date"
  )
  private LocalDateTime submitDate;

  @Column(
      name = "log",
      nullable = false,
      length = LOG_LENGTH
  )
  private String log = StringUtils.EMPTY;

  @Column(
      name = "suspicious"
  )
  private Boolean suspicious;

  public boolean isSuspicious() {
    return (suspicious != null && suspicious);
  }
}
