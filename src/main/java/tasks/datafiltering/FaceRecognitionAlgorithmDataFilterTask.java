package tasks.datafiltering;

import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@Log4j2
public class FaceRecognitionAlgorithmDataFilterTask extends AbstractDataFilterTask {
    public FaceRecognitionAlgorithmDataFilterTask(final UUID taskId) {
        super(taskId);
    }

    @Override
    public void loadData() {
        log.info("Loading data to filter for Face Recognition Algorithm.");
    }

    @Override
    public void filterData() {
        log.info("Filtering data for Face Recognition Algorithm.");
    }

    @Override
    public void execute() {
        loadData();
        filterData();
    }
}
