package ru.rshb.intech.digital.notifications.core.channel.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.rshb.intech.digital.notifications.api.channel.Channel;
import ru.rshb.intech.digital.notifications.api.channel.ChannelType;
import ru.rshb.intech.digital.notifications.api.channel.service.ChannelService;
import ru.rshb.intech.digital.notifications.api.exception.NotificationException;
import ru.rshb.intech.digital.notifications.core.channel.repository.InMemoryChannelRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ChannelServiceTest {

  private ChannelService channelService;

  @BeforeEach
  void setUp() {
    final var channelRepository = new InMemoryChannelRepository();
    this.channelService = new ChannelServiceImpl(channelRepository);
  }

  @Test
  void register() {
    final var channel1 = Mockito.mock(Channel.class);
    when(channel1.getId())
        .thenReturn("123");
    channelService.register(channel1);

    channelService.findById("123")
        .orElseThrow();
  }

  @Test
  void findById() {
    final String id = "123";
    final boolean notFound = channelService.findById(id).isEmpty();
    assertTrue(notFound);
  }

  @Test
  void getById() {
    assertThrows(NotificationException.class, () -> {
      channelService.getById("123");
    });
  }

  @Test
  void findAllByIds() {
    final Set<Channel> found = channelService.findAllByIds(Set.of("123"));
    assertNotNull(found);
    assertTrue(found.isEmpty());
  }

  @Test
  void findByType() {
    final List<Channel> found = channelService.findByType(ChannelType.EMAIL);
    assertNotNull(found);
    assertTrue(found.isEmpty());
  }

  @Test
  void findAll() {
    final List<Channel> all = channelService.findAll();
    assertNotNull(all);
    assertTrue(all.isEmpty());
  }
}