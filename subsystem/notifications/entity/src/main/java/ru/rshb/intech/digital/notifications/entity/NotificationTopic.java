package ru.rshb.intech.digital.notifications.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.BooleanUtils;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(
    schema = "digital_notifications",
    name = "topics"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class NotificationTopic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "topic_key",
      nullable = false,
      unique = true,
      length = 300
  )
  private String key;

  @OneToOne(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.EAGER
  )
  @JoinColumn(name = "template_id")
  private NotificationTemplate template;

  @Column(name = "enabled")
  private Boolean enabled;

  @Column(name = "deleted")
  private Boolean deleted;

  @Column(name = "collate")
  private Boolean collate;

  @ManyToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.EAGER
  )
  @JoinTable(
      schema = "digital_notifications",
      name = "recipient_to_topic",
      joinColumns = @JoinColumn(name = "topic_id"),
      inverseJoinColumns = @JoinColumn(name = "recipient_id")
  )
  private final Set<NotificationRecipient> recipients = new HashSet<>();


  public void addRecipient(@Nonnull NotificationRecipient recipient) {
    this.recipients.add(recipient);
  }

  public boolean isCollate() {
    return BooleanUtils.toBooleanDefaultIfNull(collate, false);
  }
}
