package ru.rshb.intech.digital.commons.forms.attributes;

import ru.rshb.intech.digital.commons.forms.Form;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormAttribute {

  Class<? extends Form<?, ?>> value();
}
