package ru.rshb.intech.digital.service.config;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import ru.rshb.intech.digital.notifications.api.NotificationService;
import ru.rshb.intech.digital.notifications.api.channel.Channel;
import ru.rshb.intech.digital.notifications.api.channel.repository.ChannelRepository;
import ru.rshb.intech.digital.notifications.api.channel.service.ChannelService;
import ru.rshb.intech.digital.notifications.api.message.queue.MessageQueue;
import ru.rshb.intech.digital.notifications.api.sender.Sender;
import ru.rshb.intech.digital.notifications.core.channel.repository.InMemoryChannelRepository;
import ru.rshb.intech.digital.notifications.core.channel.service.ChannelServiceImpl;
import ru.rshb.intech.digital.notifications.core.channel.service.NotificationServiceImpl;
import ru.rshb.intech.digital.notifications.core.message.queue.InMemoryMessageQueue;
import ru.rshb.intech.digital.notifications.core.message.scheduler.BasicQueueScheduler;
import ru.rshb.intech.digital.notifications.mail.channel.BasicEmailChannel;
import ru.rshb.intech.digital.notifications.mail.channel.EmailChannels;
import ru.rshb.intech.digital.notifications.mail.channel.EmailMessageProcessor;
import ru.rshb.intech.digital.subsystem.events.AppEventBus;

@Configuration
public class MailConfig {

  @Bean
  public NotificationService notificationService(@Nonnull ChannelService channelService) {
    return new NotificationServiceImpl(channelService);
  }

  @Bean
  public ChannelService channelService(@Nonnull ChannelRepository channelRepository,
                                       @Nonnull JavaMailSender defaultMailSender,
                                       @Value("${spring.mail.username}") String defaultSender,
                                       @Value("${subscriptions.sender.title}") String senderTitle,
                                       @Nonnull AppEventBus appEventBus) {

    final var channelService = new ChannelServiceImpl(channelRepository);

    channelService.register(

        emailChannel(
            EmailChannels.DEFAULT,
            defaultMailSender,
            defaultSender,
            senderTitle,
            appEventBus
        ),

        emailChannel(
            EmailChannels.MAILING,
            defaultMailSender,
            defaultSender,
            senderTitle,
            appEventBus
        ) // TODO использовать другой mailSender с другим отправителем для рассылки
    );

    return channelService;
  }

  @Nonnull
  private Channel emailChannel(@Nonnull String channelId,
                               @Nonnull JavaMailSender mailSender,
                               @Nonnull String senderAddress,
                               @Nonnull String senderTitle,
                               @Nonnull AppEventBus appEventBus) {

    final var senderInfo = new Sender(senderAddress, senderTitle, true);
    final var queue = messageQueue(mailSender, appEventBus);
    return new BasicEmailChannel(channelId, senderInfo, queue);
  }

  @Nonnull
  private MessageQueue messageQueue(@Nonnull JavaMailSender mailSender,
                                    @Nonnull AppEventBus appEventBus) {
    return new InMemoryMessageQueue(
        new EmailMessageProcessor(mailSender),
        new BasicQueueScheduler(),
        appEventBus
    );
  }

  @Bean
  public ChannelRepository channelRepository() {
    return new InMemoryChannelRepository();
  }
}
