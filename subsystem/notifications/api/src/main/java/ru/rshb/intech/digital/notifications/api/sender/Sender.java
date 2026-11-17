package ru.rshb.intech.digital.notifications.api.sender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.annotation.Nonnull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sender {

  private String id;
  private String title;
  private boolean useTitle;

  public Sender(@Nonnull Sender otherSender) {
    this(
        otherSender.getId(),
        otherSender.getTitle(),
        otherSender.isUseTitle()
    );
  }
}
