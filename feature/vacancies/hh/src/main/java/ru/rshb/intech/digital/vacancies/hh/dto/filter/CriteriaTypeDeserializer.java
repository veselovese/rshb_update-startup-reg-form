package ru.rshb.intech.digital.vacancies.hh.dto.filter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CriteriaTypeDeserializer extends StdDeserializer<CriteriaType> {


  protected CriteriaTypeDeserializer() {
    super(CriteriaType.class);
  }

  @Override
  public CriteriaType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {

    final var strValue = jsonParser.getValueAsString();
    return CriteriaType.parse(strValue);
  }
}
