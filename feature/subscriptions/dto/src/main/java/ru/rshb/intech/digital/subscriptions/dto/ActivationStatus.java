package ru.rshb.intech.digital.subscriptions.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Статус активации подписки")
public enum ActivationStatus {

  SUBSCRIBED,

  UNSUBSCRIBED
}
