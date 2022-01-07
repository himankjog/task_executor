package tasks;

import enums.TaskType;

import java.util.UUID;

public interface Task {
    UUID getTaskId();
    TaskType getTaskType();
    void execute();
}
