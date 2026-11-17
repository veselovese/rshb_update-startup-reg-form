package ru.rshb.intech.digital.service.content.vacancy.job.db;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import ru.rshb.intech.digital.service.content.vacancy.job.VacancyJobContext;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.VacancyExtendedDto;

import java.util.LinkedList;
import java.util.Queue;

@RequiredArgsConstructor
public class VacancyStoreReader implements ItemReader<VacancyExtendedDto> {

    private final VacancyJobContext context;
    private Queue<VacancyExtendedDto> vacancyQueue;

    @Override
    public VacancyExtendedDto read() throws Exception {
        if (vacancyQueue == null) {
            vacancyQueue = new LinkedList<>(context.getDetailedVacancies().values());
        }
        return vacancyQueue.poll();
    }
}
