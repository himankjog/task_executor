package tasks.datagathering;

import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@Log4j2
public class RecommendationGatherDataTask extends AbstractGatherDataTask {
    public RecommendationGatherDataTask(final UUID taskId) {
        super(taskId);
    }

    @Override
    public void gatherData() {
        log.info("Gathering data for recommendation.");
    }

    @Override
    public void execute() {
        gatherData();
    }
}
