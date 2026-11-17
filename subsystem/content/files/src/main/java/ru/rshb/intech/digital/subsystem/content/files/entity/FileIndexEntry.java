package ru.rshb.intech.digital.subsystem.content.files.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rshb.intech.digital.notifications.entity.NotificationTemplate;

@Entity
@Table(
    schema = "digital_content",
    name = "shared_files_index"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class FileIndexEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      nullable = false
  )
  private String externalId;

  @Column(
      name = "title",
      nullable = false
  )
  private String title;

  @Column(
      name = "url",
      nullable = false
  )
  private String url;

  @Column(
      name = "dnld_limit"
  )
  private Integer downloadLimit;

  @OneToOne(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.EAGER
  )
  @JoinColumn(name = "ext_notif_templ_id")
  private NotificationTemplate externalNotificationTemplate;
}
