package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.contests.patent.repository.ClientNumberRepository;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.exception.PublicationValidationException;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;

/**
 * Атрибут "Количество клиентов".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormClientsNumber")
@FormAttribute(PatentPublicationForm.class)
public class ClientsNumber extends TextAttributeBase<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "patents.contest.registration.form.attribute.clients.number";
  private static final int MAX_LENGTH = 100;

  public ClientsNumber(@Nonnull ClientNumberRepository clientNumberRepository) {
    super(TITLE_KEY, MAX_LENGTH, new ClientsNumberMapper(clientNumberRepository));
  }

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getClientsNumVariantId();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setClientsAmount(outputValue);
  }

  @RequiredArgsConstructor
  private static class ClientsNumberMapper implements ValueMapper<String, String> {

    private final ClientNumberRepository clientNumberRepository;

    @Override
    public <D, E> String map(String rawValue, @Nonnull FormContext<D, E> context) {
      return clientNumberRepository.findByExternalIdIgnoreCase(rawValue)
          .map(option -> option.getValue())
          .orElseThrow(PublicationValidationException::invalidClientsNumber);
    }
  }
}
