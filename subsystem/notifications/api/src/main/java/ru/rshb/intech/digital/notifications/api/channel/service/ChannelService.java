package ru.rshb.intech.digital.notifications.api.channel.service;

import ru.rshb.intech.digital.notifications.api.channel.Channel;
import ru.rshb.intech.digital.notifications.api.channel.ChannelType;
import ru.rshb.intech.digital.notifications.api.exception.NotificationException;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ChannelService {

  void register(@Nonnull Channel... channels);

  @Nonnull
  Optional<Channel> findById(@Nonnull String channelId);

  @Nonnull
  default Channel getById(@Nonnull String channelId) {
    return findById(channelId)
        .orElseThrow(() -> new NotificationException("Cannot find channel: " + channelId));
  }

  @Nonnull
  Set<Channel> findAllByIds(@Nonnull Set<String> channelIds);

  @Nonnull
  List<Channel> findByType(@Nonnull ChannelType channelType);

  @Nonnull
  List<Channel> findAll();
}
