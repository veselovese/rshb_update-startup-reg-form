package ru.rshb.intech.digital.vacancies.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    schema = "digital_vacancies",
    name = "o_plt_companies"
)
@Getter
@Builder
@Generated
@AllArgsConstructor
public class Company {

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

  @OneToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.LAZY,
      mappedBy = "company"
  )
  private Set<Vacancy> vacancies;


  public Company() {
    this.vacancies = new HashSet<>();
  }
}
