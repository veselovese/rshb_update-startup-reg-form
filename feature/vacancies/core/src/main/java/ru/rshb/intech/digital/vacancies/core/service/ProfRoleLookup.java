package ru.rshb.intech.digital.vacancies.core.service;

import ru.rshb.intech.digital.vacancies.hh.dto.profrole.ProfRoleCategoryDto;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProfRoleLookup {

  private final Map<String, ProfRoleCategoryDto> categoriesMap;

  public ProfRoleLookup(@Nonnull List<ProfRoleCategoryDto> rolesCategories) {
    this.categoriesMap = rolesCategories.stream()
        .collect(Collectors.toMap(ProfRoleCategoryDto::getId, Function.identity()));
  }

  @Nonnull
  public Optional<ProfRoleCategoryDto> findCategoryByRoleId(@Nonnull String profRoleId) {
    return categoriesMap.values()
        .stream()
        .filter(category -> isCategoryContainRole(category, profRoleId))
        .findFirst();
  }

  private boolean isCategoryContainRole(@Nonnull ProfRoleCategoryDto category, @Nonnull String profRoleId) {
    return category.getRoles()
        .stream()
        .anyMatch(role -> role.getId().equalsIgnoreCase(profRoleId));
  }
}
