package ru.rshb.intech.digital.notifications.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplatedMessage {

  private MessageTemplate template;

  private boolean useDefaultSubject;

  private Set<String> recipients;

  private boolean collate;
}
