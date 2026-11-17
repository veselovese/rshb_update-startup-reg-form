package ru.rshb.intech.digital.notifications.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    schema = "digital_notifications",
    name = "recipients"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotificationRecipient extends NotificationReceiver {

  @ManyToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.LAZY,
      mappedBy = "recipients"
  )
  private Set<NotificationTopic> topics = new HashSet<>();


  public void addTopic(@Nonnull NotificationTopic topic) {
    this.topics.add(topic);
    topic.addRecipient(this);
  }
}
