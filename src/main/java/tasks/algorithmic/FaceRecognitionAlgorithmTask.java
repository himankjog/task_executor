package tasks.algorithmic;

import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@Log4j2
public class FaceRecognitionAlgorithmTask extends AbstractAlgorithmicTask {

    public FaceRecognitionAlgorithmTask(final UUID taskId) {
        super(taskId);
    }

    @Override
    public void selectTrainedModel() {
        log.info("Selecting Trained Model for Face Recognition Algorithm.");
    }

    @Override
    public void validateDataForAlgorithm() {
        log.info("Validated data for Face Recognition Algorithm task.");
    }

    @Override
    public void submitTaskToTrainedModel() {
        log.info("Submitted task to run for Face Recognition.");
    }

    @Override
    public void execute() {
        selectTrainedModel();
        validateDataForAlgorithm();
        submitTaskToTrainedModel();
    }
}
