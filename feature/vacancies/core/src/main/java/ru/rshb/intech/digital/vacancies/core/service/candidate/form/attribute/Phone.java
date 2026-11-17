package ru.rshb.intech.digital.vacancies.core.service.candidate.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.vacancies.core.service.candidate.form.VacancyCandidateForm;
import ru.rshb.intech.digital.vacancies.entity.CandidateCv;
import ru.rshb.intech.digital.vacancies.model.CandidateCvDto;

@Component("vacancyCandidateCvFormPhone")
@FormAttribute(VacancyCandidateForm.class)
public class Phone extends PhoneAttributeBase<CandidateCvDto, CandidateCv> {

  @Override
  public String supplyInputValue(CandidateCvDto inputData) {
    return inputData.getPhone();
  }

  @Override
  public void consumeOutputValue(String outputValue, CandidateCv outputData) {
    outputData.setPhone(outputValue);
  }
}
