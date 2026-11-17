package ru.rshb.intech.digital.notifications.api.channel.repository;

import ru.rshb.intech.digital.notifications.api.channel.Channel;
import ru.rshb.intech.digital.notifications.api.channel.ChannelType;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ChannelRepository {

  @Nonnull
  Channel save(@Nonnull Channel channel);

  @Nonnull
  Optional<Channel> findById(@Nonnull String channelId);

  @Nonnull
  Set<Channel> findAllByIds(@Nonnull Set<String> channelIds);

  @Nonnull
  List<Channel> findByType(@Nonnull ChannelType channelType);

  @Nonnull
  List<Channel> findAll();
}
