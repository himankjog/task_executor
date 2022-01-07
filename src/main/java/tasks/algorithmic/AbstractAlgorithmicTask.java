package tasks.algorithmic;

import enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class AbstractAlgorithmicTask implements AlgorithmicTask {
    private final UUID taskId;

    @Override
    public TaskType getTaskType() {
        return TaskType.ALGORITHMIC_TASK;
    }
}

