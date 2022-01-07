package tasks.algorithmic;

import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@Log4j2
public class RecommendationAlgorithmTask extends AbstractAlgorithmicTask {

    public RecommendationAlgorithmTask(final UUID taskId) {
        super(taskId);
    }

    @Override
    public void selectTrainedModel() {
        log.info("Selected trained model for Recommendation Algorithm.");
    }

    @Override
    public void validateDataForAlgorithm() {
        log.info("Validated data for Recommendation Algorithm.");
    }

    @Override
    public void submitTaskToTrainedModel() {
        log.info("Submitted task for Recommendation Algorithm.");
    }

    @Override
    public void execute() {
        selectTrainedModel();
        validateDataForAlgorithm();
        checkCache();
        submitTaskToTrainedModel();
    }

    private void checkCache() {
        log.info("Checking cache for recommendation algorithm.");
    }
}
