package ru.rshb.intech.digital.feature.downloads.entity;

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
import ru.rshb.intech.digital.subsystem.content.files.entity.FileIndexEntry;

import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_content",
    name = "file_request"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class FileRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "submit_dt",
      nullable = false
  )
  private LocalDateTime submitDate;

  @ManyToOne(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "file_id")
  private FileIndexEntry file;

  @Column(
      name = "full_name",
      nullable = false
  )
  private String fullName;

  @Column(
      name = "phone",
      nullable = false
  )
  private String phone;

  @Column(
      name = "email",
      nullable = false
  )
  private String email;

  @Column(name = "org")
  private String organization;

  private String position;

  @Column(
      name = "suspicious",
      nullable = false
  )
  private Boolean suspicious = false;
}
