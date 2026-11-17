package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_contests",
    name = "agrotech_olympiad_reg"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class AgrotechOlympiadRegistration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "submit_dt")
  private LocalDateTime submitDate;

  @Column(name = "applicant_name")
  private String applicantName;

  private String email;

  private String phone;

  @Column(name = "vk_url")
  private String vkProfileUrl;

  private String directions;

  @ManyToOne(
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST
      },
      fetch = FetchType.EAGER
  )
  @JoinColumn(name = "university_id")
  private University university;

  private Boolean suspicious = false;
}
