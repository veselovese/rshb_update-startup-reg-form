package ru.rshb.intech.digital.subscriptions.core.mapper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.subscriptions.dto.UnsubscribeFeedbackDto;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionFeedback;

import java.util.List;

@Component
public class SubscriptionFeedbackReportMapper {

  private static final int MAX_REASONS_LEN = 1000;
  private static final int MAX_FEEDBACK_LEN = 1000;

  @Nonnull
  public SubscriptionFeedback toEntityFromDto(@Nonnull UnsubscribeFeedbackDto dto) {
    final var entity = new SubscriptionFeedback();
    entity.setReasons(
        limit(joinReasons(dto.getReasons()), MAX_REASONS_LEN)
    );
    entity.setFeedback(
        limit(dto.getFeedback(), MAX_FEEDBACK_LEN)
    );
    return entity;
  }

  @Nullable
  private String joinReasons(@Nullable List<String> reasons) {

    if (CollectionUtils.isEmpty(reasons)) {
      return null;
    }

    return String.join(",", reasons);
  }

  private String limit(String value, int limit) {
    return StringUtils.left(value, limit);
  }
}
