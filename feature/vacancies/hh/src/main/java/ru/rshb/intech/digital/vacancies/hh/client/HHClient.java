package ru.rshb.intech.digital.vacancies.hh.client;

import jakarta.annotation.Nonnull;
import org.springframework.web.client.RestTemplate;

public class HHClient {

  private final HHAuthClient authClient;
  private final HHVacanciesClient vacanciesClient;
  private final HHSpecializationsClient specializationsClient;
  private final HHProfessionalRolesClient professionalRolesClient;


  public HHClient(@Nonnull RestTemplate restTemplate,
                  @Nonnull HHClientOptions options) {

    this.authClient = new HHAuthClient(restTemplate, options);
    this.vacanciesClient = new HHVacanciesClient(restTemplate, options);
    this.specializationsClient = new HHSpecializationsClient(restTemplate, options);
    this.professionalRolesClient = new HHProfessionalRolesClient(restTemplate, options);
  }


  @Nonnull
  public HHAuthClient authentication() {
    return authClient;
  }

  @Nonnull
  public HHVacanciesClient vacancies() {
    return vacanciesClient;
  }

  @Nonnull
  public HHSpecializationsClient specializations() {
    return specializationsClient;
  }

  @Nonnull
  public HHProfessionalRolesClient professionalRoles() {
    return professionalRolesClient;
  }
}
