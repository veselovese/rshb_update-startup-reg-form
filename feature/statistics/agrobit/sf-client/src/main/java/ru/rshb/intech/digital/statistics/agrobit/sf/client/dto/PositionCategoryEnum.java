package ru.rshb.intech.digital.statistics.agrobit.sf.client.dto;

import lombok.Getter;

import jakarta.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

@Getter
public enum PositionCategoryEnum implements PositionCategory { // TODO Вынести в справочник

  ALL("Все"),

  CROP_PRODUCTION(
      "Растениеводство",
      "Агроном",
      "Семеновод",
      "Тракторист",
      "Комбайнер",
      "Мелиоратор",
      "Почвовед",
      "Агрохимик",
      "Овощевод",
      "Селекционер",
      "Растениевод",
      "Цветовод"
  ),

  LIVESTOCK(
      "Животноводство",
      "Ветеринар",
      "Генетик",
      "Зоотехник",
      "Пчеловод",
      "Птицевод",
      "Механизатор",
      "Селекционер",
      "Животновод"
  ),

  AQUACULTURE(
      "Рыбоводство",
      "Рыбовод",
      "Ихтиолог",
      "Рыбак",
      "Рыболов",
      "Обработчик рыбы"
  );

  private final String categoryName;
  private final List<String> positions;

  PositionCategoryEnum(String categoryName, String... positions) {
    this.categoryName = categoryName;
    this.positions = Arrays.asList(positions);
  }

  @Nonnull
  public static List<PositionCategory> all() {
    return Arrays.asList(values());
  }

  @Nonnull
  public static PositionCategory parse(@Nonnull String catName) {
    return Arrays.stream(values())
        .filter(cat -> cat.getCategoryName().equalsIgnoreCase(catName))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Unknown category: " + catName));
  }
}
