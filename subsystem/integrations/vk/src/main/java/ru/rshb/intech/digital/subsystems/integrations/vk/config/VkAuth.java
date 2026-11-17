package ru.rshb.intech.digital.subsystems.integrations.vk.config;

import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VkAuth {

  private final boolean disabled;
  private final UserActor userActor;
  private final ServiceActor serviceActor;

  public VkAuth(int appId, @Nonnull String token) {
    this(false, new UserActor(appId, token), new ServiceActor(appId, token));
  }

  @Nonnull
  public static VkAuth createDisabled() {
    return new VkAuth(true, null, null);
  }
}
