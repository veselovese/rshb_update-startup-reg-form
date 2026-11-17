package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;

public abstract class InternshipRegistrationAttribute<DV, EV>
    extends Attribute<InternshipRegistrationDto, DV, Registration, EV> {

  public InternshipRegistrationAttribute(
      @Nonnull AttributeParams<InternshipRegistrationDto, DV, Registration, EV> params) {

    super(params);
  }
}
