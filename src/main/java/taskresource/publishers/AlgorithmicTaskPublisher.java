package taskresource.publishers;

import lombok.extern.log4j.Log4j2;
import taskresource.TaskQueue;
import tasks.Task;
import tasks.algorithmic.AlgorithmicTask;
import tasks.algorithmic.FaceRecognitionAlgorithmTask;
import tasks.algorithmic.RecommendationAlgorithmTask;
import utils.CancellationToken;

import java.util.Random;
import java.util.UUID;

@Log4j2
public class AlgorithmicTaskPublisher extends AbstractPublisher {

    public AlgorithmicTaskPublisher() {
        super(new CancellationToken());
    }

    @Override
    protected Task getTask() {
        AlgorithmicTask algorithmicTask;
        if (new Random().nextBoolean()) {
            algorithmicTask = new FaceRecognitionAlgorithmTask(UUID.randomUUID());
        } else {
            algorithmicTask = new RecommendationAlgorithmTask(UUID.randomUUID());
        }
        return algorithmicTask;
    }

    @Override
    public void publish(final Task task) {
        log.info("Publishing Algorithmic Task: {}", task.getTaskId());
        getTaskQueue().push(task);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            log.error("Some issue while sleeping the Algorithmic Task publisher");
        }
    }
}
