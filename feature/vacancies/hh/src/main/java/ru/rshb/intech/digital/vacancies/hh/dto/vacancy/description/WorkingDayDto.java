package ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkingDayDto {

    private String id;
    private String name;
}
