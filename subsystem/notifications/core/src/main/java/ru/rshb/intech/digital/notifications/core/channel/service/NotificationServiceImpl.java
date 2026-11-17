package ru.rshb.intech.digital.notifications.core.channel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.rshb.intech.digital.notifications.api.NotificationService;
import ru.rshb.intech.digital.notifications.api.channel.service.ChannelService;
import ru.rshb.intech.digital.notifications.api.message.Message;

import jakarta.annotation.Nonnull;


@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private final ChannelService channelService;

  @Override
  public void send(@Nonnull Message message) {

    log.debug("Searching channels...");
    final var channels = message.getChannels()
        .stream()
        .map(channelService::getById)
        .toList();
    log.debug("Channels found: {}", channels.size());

    for (final var channel : channels) {
      log.debug("Appending message to channel {} ...", channel.getId());
      channel.append(message);
      log.debug("Appending completed");
    }
  }
}
