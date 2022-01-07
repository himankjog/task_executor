package taskresource.publishers;

import lombok.extern.log4j.Log4j2;
import taskresource.TaskQueue;
import tasks.Task;
import tasks.datafiltering.DataFilterTask;
import tasks.datafiltering.FaceRecognitionAlgorithmDataFilterTask;
import tasks.datafiltering.RecommendationAlgorithmDataFilterTask;
import utils.CancellationToken;

import java.util.Random;
import java.util.UUID;

@Log4j2
public class DataFilteringTaskPublisher extends AbstractPublisher {
    public DataFilteringTaskPublisher() {
        super(new CancellationToken());
    }

    @Override
    protected Task getTask() {
        DataFilterTask dataFilterTask;
        if (new Random().nextBoolean()) {
            dataFilterTask = new FaceRecognitionAlgorithmDataFilterTask(UUID.randomUUID());
        } else {
            dataFilterTask = new RecommendationAlgorithmDataFilterTask(UUID.randomUUID());
        }

        return dataFilterTask;
    }

    @Override
    public void publish(final Task task) {
        log.info("Publishing Data Filtering Task: {}", task.getTaskId());
        getTaskQueue().push(task);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            log.error("Some issue while sleeping the Data Filtering Task publisher");
        }
    }
}
