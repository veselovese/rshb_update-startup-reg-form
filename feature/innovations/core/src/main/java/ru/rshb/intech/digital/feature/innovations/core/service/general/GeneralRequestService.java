package ru.rshb.intech.digital.feature.innovations.core.service.general;


import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.innovations.core.entity.GeneralRequest;

public interface GeneralRequestService {

  @Nonnull
  GeneralRequest save(@Nonnull GeneralRequest generalRequest);

  boolean isRequestExist(@Nonnull String phone);

  @Nonnull
  String[] getFormAttachmentAllowedExtensions();
}
