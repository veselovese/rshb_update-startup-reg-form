package ru.rshb.intech.digital.commons.validation.vk;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VkUrlValidator.class)
@Repeatable(VkUrl.List.class)
public @interface VkUrl {

  String message() default "Bad VK URL format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};


  @Target({ElementType.FIELD, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {

    VkUrl[] value();
  }
}
