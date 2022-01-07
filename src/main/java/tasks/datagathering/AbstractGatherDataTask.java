package tasks.datagathering;

import enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class AbstractGatherDataTask implements GatherDataTask {
    private final UUID taskId;

    @Override
    public TaskType getTaskType() {
        return TaskType.GATHER_DATA_TASK;
    }
}
