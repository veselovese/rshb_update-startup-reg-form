package ru.rshb.intech.digital.notifications.core.channel.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import ru.rshb.intech.digital.notifications.api.NotificationService;
import ru.rshb.intech.digital.notifications.api.channel.Channel;
import ru.rshb.intech.digital.notifications.api.channel.ChannelType;
import ru.rshb.intech.digital.notifications.api.channel.service.ChannelService;
import ru.rshb.intech.digital.notifications.api.message.BasicMessage;
import ru.rshb.intech.digital.notifications.api.message.MessageContent;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageProcessor;
import ru.rshb.intech.digital.notifications.api.message.queue.MessageQueue;
import ru.rshb.intech.digital.notifications.api.message.scheduler.MessageQueueScheduler;
import ru.rshb.intech.digital.notifications.api.sender.Sender;
import ru.rshb.intech.digital.notifications.core.channel.BasicChannel;
import ru.rshb.intech.digital.notifications.core.message.processor.DebugMessageProcessor;
import ru.rshb.intech.digital.notifications.core.message.scheduler.BasicQueueScheduler;
import ru.rshb.intech.digital.subsystem.audit.notifications.NotificationLog;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executors;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@Disabled
class NotificationServiceTest {

  @Test
  void send() {

    final var channelService = Mockito.mock(ChannelService.class);
    when(channelService.findAllByIds(anySet()))
        .thenReturn(Set.of());

    final NotificationService service = new NotificationServiceImpl(channelService);

    final var message = BasicMessage.builder()
        .channels(Set.of("123"))
        .recipients(Set.of("boris.kuznetsov@cinimex.ru"))
        .collate(false)
        .content(
            MessageContent.builder()
                .subject("Hello")
                .data("test".getBytes(StandardCharsets.UTF_8))
                .mediaType(MediaType.TEXT_PLAIN)
                .attachments(Collections.emptyList())
                .build()
        )
        .build();

    service.send(message);
  }

  @Test
  void send_debug() throws InterruptedException {

    final MessageProcessor processor = new DebugMessageProcessor();
    final MessageQueueScheduler scheduler = new BasicQueueScheduler();
    final NotificationLog notificationLog = Mockito.mock(NotificationLog.class);
    final ApplicationEventPublisher applicationEventPublisher = Mockito.mock(ApplicationEventPublisher.class);

    final MessageQueue queue = null; //new InMemoryMessageQueue(processor, scheduler, applicationEventPublisher, notificationLog);
    final Channel channel = BasicChannel.builder()
        .id("123")
        .type(ChannelType.EMAIL)
        .sender(
            Sender.builder()
                .id("piligrim365@yandex.ru")
                .build()
        )
        .messageQueue(queue)
        .build();

    final ChannelService channelService = Mockito.mock(ChannelService.class);
    when(channelService.findAllByIds(anySet()))
        .thenReturn(Set.of(channel));

    final NotificationService service = new NotificationServiceImpl(channelService);
    final var message = BasicMessage.builder()
        .channels(Set.of("123"))
        .recipients(Set.of("boris.kuznetsov@cinimex.ru"))
        .collate(false)
        .content(
            MessageContent.builder()
                .subject("Hello")
                .data("test".getBytes(StandardCharsets.UTF_8))
                .mediaType(MediaType.TEXT_PLAIN)
                .attachments(Collections.emptyList())
                .build()
        )
        .build();

    final var pool1 = Executors.newFixedThreadPool(10);
    final var pool2 = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 1000; i++) {
      pool1.submit(() -> {
        service.send(message);
      });
      pool2.submit(() -> {
        service.send(message);
      });
    }

    Thread.sleep(1_000 * 60 * 60);
  }
}