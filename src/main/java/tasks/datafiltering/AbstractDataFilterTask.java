package tasks.datafiltering;

import enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class AbstractDataFilterTask implements DataFilterTask {
    private final UUID taskId;

    @Override
    public TaskType getTaskType() {
        return TaskType.DATA_FILTER_TASK;
    }
}
