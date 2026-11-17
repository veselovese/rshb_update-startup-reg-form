package ru.rshb.intech.digital.service.scheduler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.concurrent.ScheduledFuture;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ScheduledTask {
    private String id;
    private String name;
    private TaskType type;
    private String options;
    private ScheduledFuture<?> future;
}
