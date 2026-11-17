package ru.rshb.intech.digital.feature.contests.chef.entity;

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
    schema = "digital_contests",
    name = "o_plt_chef_contestants"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class ChefContestant {

  public static final int NAME_LENGTH = 300;
  public static final int PHONE_LENGTH = 15;
  public static final int RESULT_LENGTH = 255;
  public static final int SOLUTION_URL_LENGTH = 1000;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "submit_date",
      nullable = false
  )
  private LocalDateTime submitDate;

  @Column(
      name = "contestant_name",
      length = NAME_LENGTH,
      nullable = false
  )
  private String name;

  @Column(
      name = "phone",
      nullable = false,
      length = PHONE_LENGTH,
      unique = true
  )
  private String phone;

  @Column(
      name = "result",
      nullable = false,
      length = RESULT_LENGTH
  )
  private String result;


  @Column(
      name = "solution_url",
      nullable = false,
      length = SOLUTION_URL_LENGTH
  )
  private String solutionUrl;

  @Column(
      name = "suspicious"
  )
  private Boolean suspicious;

  public boolean isSuspicious() {
    return (suspicious != null && suspicious);
  }
}
