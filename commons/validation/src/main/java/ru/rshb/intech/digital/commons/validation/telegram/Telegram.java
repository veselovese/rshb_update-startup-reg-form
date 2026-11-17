package ru.rshb.intech.digital.commons.validation.telegram;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelegramValidator.class)
@Repeatable(Telegram.List.class)
public @interface Telegram {

  String message() default "Bad Telegram link format";

  Class<?>[] groups() default { } ;

  Class<? extends Payload>[] payload() default { };

  @Target({ FIELD, PARAMETER })
  @Retention(RUNTIME)
  @Documented
  @interface List {
    Telegram[] value();
  }
}
