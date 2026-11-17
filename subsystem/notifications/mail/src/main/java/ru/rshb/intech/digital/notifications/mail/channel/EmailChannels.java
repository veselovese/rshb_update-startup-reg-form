package ru.rshb.intech.digital.notifications.mail.channel;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ID каналов email уведомлений.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EmailChannels {

  /**
   * Служебные уведомления.
   */
  public static final String DEFAULT = "email.default";

  /**
   * Email рассылка.
   */
  public static final String MAILING = "email.mailing";
}
