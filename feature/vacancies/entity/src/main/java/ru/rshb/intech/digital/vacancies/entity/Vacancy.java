package ru.rshb.intech.digital.vacancies.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    schema = "digital_vacancies",
    name = "o_plt_vacancies"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode
public class Vacancy {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      length = 100,
      unique = true,
      nullable = false
  )
  private String externalId;

  @Column(
      name = "title",
      length = 300,
      nullable = false
  )
  private String title;

  @Column(
      name = "deleted",
      nullable = false
  )
  private Boolean deleted;

  @Column(
      name = "description"
  )
  private String description;

  @Column(
      name = "description_html"
  )
  private String descriptionHtml;

  @Column(
      name = "url",
      length = 500,
      nullable = false
  )
  private String url;

  @Column(
      name = "apply_url",
      length = 500,
      nullable = false
  )
  private String applyUrl;

  @Column(
      name = "publish_date",
      nullable = false
  )
  private ZonedDateTime publishDate;

  @ManyToOne(
      fetch = FetchType.LAZY
  )
  private Company company;

  @ManyToOne(
      fetch = FetchType.LAZY
  )
  private Area area;

  @ManyToOne(
      fetch = FetchType.LAZY
  )
  private Employment employment;

  @ManyToOne(
      fetch = FetchType.LAZY
  )
  private Experience experience;

  @ManyToMany
  @JoinTable(
      schema = "digital_vacancies",
      name = "o_plt_vacancy_to_specialization",
      joinColumns = @JoinColumn(name = "vacancy_id"),
      inverseJoinColumns = @JoinColumn(name = "specialization_id")
  )
  private Set<Specialization> specializations;


  public Vacancy() {
    this.specializations = new HashSet<>();
  }


  public void addSpecialization(Specialization specialization) {
    this.specializations.add(specialization);
    specialization.addVacancy(this);
  }

  public void addSpecializations(Collection<Specialization> specializations) {
    specializations.forEach(this::addSpecialization);
  }
}
