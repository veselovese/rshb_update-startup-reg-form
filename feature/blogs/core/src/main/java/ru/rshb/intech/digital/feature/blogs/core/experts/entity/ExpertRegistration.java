package ru.rshb.intech.digital.feature.blogs.core.experts.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.BooleanUtils;

import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_blogs",
    name = "expert_registration"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class ExpertRegistration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "submit_date",
      nullable = false
  )
  private LocalDateTime submitDate;

  @Column(
      name = "expert_name",
      nullable = false
  )
  private String expertName;

  @Column(
      name = "email",
      nullable = false
  )
  private String email;

  @Column(
      name = "phone",
      nullable = false
  )
  private String phone;

  @Column(name = "vk_url")
  private String vkUrl;

  @Column(
      name = "directions",
      nullable = false
  )
  private String directions;

  @Column(name = "performance_urls")
  private String performanceUrls;

  @Column(
      name = "comment",
      nullable = false
  )
  private String comment;

  @Column(
      name = "suspicious",
      nullable = false
  )
  private Boolean suspicious = false;

  @Nonnull
  public Boolean getSuspicious() {
    return BooleanUtils.toBoolean(suspicious);
  }
}
