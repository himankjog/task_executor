package taskresource.publishers;

import lombok.extern.log4j.Log4j2;
import taskresource.TaskQueue;
import tasks.Task;
import tasks.datagathering.FaceRecognitionGatherDataTask;
import tasks.datagathering.GatherDataTask;
import tasks.datagathering.RecommendationGatherDataTask;
import utils.CancellationToken;

import java.util.Random;
import java.util.UUID;

@Log4j2
public class DataGatheringTaskPublisher extends AbstractPublisher {
    public DataGatheringTaskPublisher() {
        super(new CancellationToken());
    }

    @Override
    protected Task getTask() {
        GatherDataTask gatherDataTask;
        if (new Random().nextBoolean()) {
            gatherDataTask = new FaceRecognitionGatherDataTask(UUID.randomUUID());
        } else {
            gatherDataTask = new RecommendationGatherDataTask(UUID.randomUUID());
        }

        return gatherDataTask;
    }

    @Override
    public void publish(Task task) {
        log.info("Publishing Data Gathering Task: {}", task.getTaskId());
        getTaskQueue().push(task);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            log.error("Some issue while sleeping the Data Gathering Task publisher");
        }
    }
}
