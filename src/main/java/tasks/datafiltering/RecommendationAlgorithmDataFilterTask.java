package tasks.datafiltering;

import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@Log4j2
public class RecommendationAlgorithmDataFilterTask extends AbstractDataFilterTask {
    public RecommendationAlgorithmDataFilterTask(final UUID taskId) {
        super(taskId);
    }

    @Override
    public void loadData() {
        log.info("Loading data to filter for the recommendation algorithm.");
    }

    @Override
    public void filterData() {
        log.info("Filtering data for the recommendation algorithm.");
    }

    @Override
    public void execute() {
        loadData();
        validateData();
        filterData();
    }

    private void validateData() {
        log.info("Validating data for recommendation algorithm data filter.");
    }
}
