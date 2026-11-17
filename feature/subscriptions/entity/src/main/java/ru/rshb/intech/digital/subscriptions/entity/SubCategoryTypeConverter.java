package ru.rshb.intech.digital.subscriptions.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Converter(autoApply = true)
public class SubCategoryTypeConverter implements AttributeConverter<CategoryType, String> {

  @Override
  public String convertToDatabaseColumn(CategoryType attribute) {
    return Optional.ofNullable(attribute)
        .map(CategoryType::getKey)
        .orElse(null);
  }

  @Override
  public CategoryType convertToEntityAttribute(String dbData) {

    if (StringUtils.isBlank(dbData)) {
      return null;
    }

    return CategoryType.parseByKey(dbData.trim());
  }
}
