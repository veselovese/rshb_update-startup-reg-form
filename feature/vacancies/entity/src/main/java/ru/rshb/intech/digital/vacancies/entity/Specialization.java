package ru.rshb.intech.digital.vacancies.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    schema = "digital_vacancies",
    name = "o_plt_specializations"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode(of = {"id", "externalId", "title"})
public class Specialization {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      unique = true,
      nullable = false,
      length = 100
  )
  private String externalId;

  @Column(
      name = "title",
      nullable = false,
      length = 300
  )
  private String title;

  @Column(
      name = "profarea_id",
      length = 50
  )
  private String profAreaId;

  @Column(
      name = "profarea_title",
      length = 100
  )
  private String profAreaTitle;

  @Column(
      name = "deleted",
      nullable = false
  )
  private Boolean deleted;

  @ManyToMany(
      mappedBy = "specializations",
      fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      }
  )
  private Set<Vacancy> vacancies;


  public Specialization() {
    this.vacancies = new HashSet<>();
  }


  public void addVacancy(Vacancy vacancy) {
    this.vacancies.add(vacancy);
  }
}
