package ru.rshb.intech.digital.subscriptions.core.service.mailing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.DataItem;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailingDigest {

  private List<DataItem> news;
  private List<DataItem> events;
  private List<DataItem> podcasts;
  private String deactivationUrl;
}
