package ru.rshb.intech.digital.censor.dictionary.event;


import org.springframework.context.ApplicationEvent;

public class InvalidateCensorDictionaryCacheEvent extends ApplicationEvent {

  public InvalidateCensorDictionaryCacheEvent(Object source) {
    super(source);
  }
}
