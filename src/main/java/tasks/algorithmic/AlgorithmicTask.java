package tasks.algorithmic;

import tasks.Task;

public interface AlgorithmicTask extends Task {
    void selectTrainedModel();
    void validateDataForAlgorithm();
    void submitTaskToTrainedModel();
}
