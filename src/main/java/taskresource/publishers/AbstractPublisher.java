package taskresource.publishers;

import lombok.extern.log4j.Log4j2;
import taskresource.TaskQueue;
import tasks.Task;
import utils.CancellationToken;


@Log4j2
public abstract class AbstractPublisher implements Publisher {
    private final CancellationToken cancellationToken;
    private final TaskQueue<Task> taskQueue;

    protected AbstractPublisher(final CancellationToken cancellationToken) {
        this.cancellationToken = cancellationToken;
        taskQueue = TaskQueue.getInstance();
    }

    @Override
    public void run() {
        while (!cancellationToken.isCancelled() && !Thread.currentThread().isInterrupted()) {
            final Task task = getTask();
            publish(task);
        }
    }
    @Override
    public void stop() {
        cancellationToken.cancel();
    }

    protected TaskQueue<Task> getTaskQueue() {
        return taskQueue;
    }

    protected abstract Task getTask();
}
