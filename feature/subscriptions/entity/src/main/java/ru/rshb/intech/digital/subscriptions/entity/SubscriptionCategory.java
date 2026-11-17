package ru.rshb.intech.digital.subscriptions.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.BooleanUtils;

@Entity
@Table(
    schema = "digital_subscriptions",
    name = "o_plt_subscription_categories"
)
@Getter
@Setter
@Builder
@Generated
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class SubscriptionCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      nullable = false,
      unique = true,
      length = 100
  )
  private String externalId;

  @Column(
      name = "title",
      nullable = false,
      length = 500
  )
  private String title;

  @Column(
      name = "cat_type",
      nullable = false
  )
  private CategoryType type;

  @Column(name = "deleted")
  private Boolean deleted;


  public SubscriptionCategory() {
    this.deleted = false;
  }

  public SubscriptionCategory(@Nonnull CategoryType categoryType, @Nonnull String externalId) {
    this.type = categoryType;
    this.externalId = externalId;
    this.deleted = false;
  }

  public boolean isDeleted() {
    return BooleanUtils.toBoolean(deleted);
  }

  @Override
  public String toString() {
    return title;
  }
}
