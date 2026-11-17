package ru.rshb.intech.digital.puzzles.entity;

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
    schema = "digital_puzzles",
    name = "o_plt_crossword_participants"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class CrosswordParticipant {

  public static final int PARTICIPANT_NAME_LENGTH = 500;
  public static final int PHONE_LENGTH = 15;
  public static final int EMAIL_LENGTH = 254;
  public static final int ABOUT_LENGTH = 1000;
  public static final int LOG_LENGTH = 1000;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "participant_name",
      nullable = false,
      length = PARTICIPANT_NAME_LENGTH
  )
  private String participantName;

  @Column(
      name = "phone",
      nullable = false,
      unique = true,
      length = PHONE_LENGTH
  )
  private String phone;

  @Column(
      name = "email",
      nullable = false,
      length = EMAIL_LENGTH
  )
  private String email;

  @Column(name = "about")
  private String about;

  @Column(name = "submit_date")
  private LocalDateTime submitDate;

  @Column(
      name = "log",
      length = LOG_LENGTH
  )
  private String log;

  @Column(
      name = "suspicious"
  )
  private Boolean suspicious;

  public boolean isSuspicious() {
    return (suspicious != null && suspicious);
  }
}
