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
    name = "o_plt_window_demands"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class InnovationWindowDemand {

  public static final int EXTERNAL_ID_LENGTH = 100;
  public static final int COMPANY_NAME_LENGTH = 200;
  public static final int COMPANY_SITE_LENGTH = 500;
  public static final int SUMMARY_LENGTH = 300;
  public static final int DESCRIPTION_LENGTH = 1000;
  public static final int PHONE_LENGTH = 15;
  public static final int EMAIL_LENGTH = 254;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      nullable = false,
      unique = true,
      length = EXTERNAL_ID_LENGTH
  )
  private String externalId;

  @Column(
      name = "submit_date",
      nullable = false
  )
  private LocalDateTime submitDate;

  @Column(
      name = "company_name",
      nullable = false,
      length = COMPANY_NAME_LENGTH
  )
  private String companyName;

  @Column(
      name = "company_site",
      nullable = false,
      length = COMPANY_SITE_LENGTH
  )
  private String companySite;

  @Column(
      name = "summary",
      nullable = false,
      length = SUMMARY_LENGTH
  )
  private String summary;

  @Column(
      name = "description",
      nullable = false,
      length = DESCRIPTION_LENGTH
  )
  private String description;

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
