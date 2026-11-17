package ru.rshb.intech.digital.notifications.core.channel.repository;

import ru.rshb.intech.digital.notifications.api.channel.Channel;
import ru.rshb.intech.digital.notifications.api.channel.ChannelType;
import ru.rshb.intech.digital.notifications.api.channel.repository.ChannelRepository;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryChannelRepository implements ChannelRepository {

  private final Map<String, Channel> channelMap;

  public InMemoryChannelRepository() {
    this.channelMap = new ConcurrentHashMap<>();
  }

  @Nonnull
  @Override
  public Channel save(@Nonnull Channel channel) {
    if (channelMap.containsKey(channel.getId())) {
      throw new IllegalStateException("Channel " + channel.getId() + " already exists");
    }
    channelMap.put(channel.getId(), channel);
    return channel;
  }

  @Nonnull
  @Override
  public Optional<Channel> findById(@Nonnull String channelId) {
    return Optional.ofNullable(channelMap.get(channelId));
  }

  @Nonnull
  @Override
  public Set<Channel> findAllByIds(@Nonnull Set<String> channelIds) {
    return channelMap.values().stream()
        .filter(v -> channelIds.contains(v.getId()))
        .collect(Collectors.toSet());
  }

  @Nonnull
  @Override
  public List<Channel> findByType(@Nonnull ChannelType channelType) {
    return channelMap.values()
        .stream()
        .filter(c -> c.getType() == channelType)
        .toList();
  }

  @Nonnull
  @Override
  public List<Channel> findAll() {
    return channelMap.values()
        .stream()
        .toList();
  }
}
