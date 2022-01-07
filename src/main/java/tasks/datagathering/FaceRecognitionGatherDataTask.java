package tasks.datagathering;

import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@Log4j2
public class FaceRecognitionGatherDataTask extends AbstractGatherDataTask {
    public FaceRecognitionGatherDataTask(final UUID taskId) {
        super(taskId);
    }

    @Override
    public void gatherData() {
        log.info("Gathering data for face recognition.");
    }

    @Override
    public void execute() {
        gatherData();
    }
}
