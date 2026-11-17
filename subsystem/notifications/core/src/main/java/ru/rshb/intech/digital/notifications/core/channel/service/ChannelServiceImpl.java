package ru.rshb.intech.digital.notifications.core.channel.service;

import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.notifications.api.channel.Channel;
import ru.rshb.intech.digital.notifications.api.channel.ChannelType;
import ru.rshb.intech.digital.notifications.api.channel.repository.ChannelRepository;
import ru.rshb.intech.digital.notifications.api.channel.service.ChannelService;

import jakarta.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

  private final ChannelRepository channelRepository;


  @Override
  public void register(@Nonnull Channel... channels) {
    Arrays.stream(channels)
        .forEach(channelRepository::save);
  }

  @Nonnull
  @Override
  public Optional<Channel> findById(@Nonnull String channelId) {
    return channelRepository.findById(channelId);
  }

  @Nonnull
  @Override
  public Set<Channel> findAllByIds(@Nonnull Set<String> channelIds) {
    return channelRepository.findAllByIds(channelIds);
  }

  @Nonnull
  @Override
  public List<Channel> findByType(@Nonnull ChannelType channelType) {
    return channelRepository.findByType(channelType);
  }

  @Nonnull
  @Override
  public List<Channel> findAll() {
    return channelRepository.findAll();
  }
}
