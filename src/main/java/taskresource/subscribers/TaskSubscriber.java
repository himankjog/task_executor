package taskresource.subscribers;

import commands.ExecutionCommand;
import commands.PendingCommandQueue;
import enums.TaskType;
import factories.Factory;
import factories.commandfactory.DefaultCommandFactory;
import lombok.extern.log4j.Log4j2;
import taskresource.TaskQueue;
import tasks.Task;
import utils.CancellationToken;

import java.util.Objects;
import java.util.Optional;

@Log4j2
public class TaskSubscriber implements Subscriber {
    private final CancellationToken cancellationToken;
    private final TaskQueue<Task> taskQueue;
    private final PendingCommandQueue<ExecutionCommand> pendingCommandQueue;
    private final Factory<ExecutionCommand, TaskType, Task> commandFactory;

    public TaskSubscriber() {
        cancellationToken = new CancellationToken();
        taskQueue = TaskQueue.getInstance();
        pendingCommandQueue = PendingCommandQueue.getInstance();
        commandFactory = new DefaultCommandFactory();
    }

    @Override
    public Task pull() {
        log.info("Subscriber getting task from TaskQueue");
        Task task = null;
        try {
            task = taskQueue.get();
        } catch (InterruptedException ex) {
            log.error("Error getting task from TaskQueue");
        }
        return task;
    }

    @Override
    public void stop() {
        cancellationToken.cancel();
    }


    @Override
    public void run() {
        while(!cancellationToken.isCancelled() && !Thread.currentThread().isInterrupted()) {
            final Task task = pull();

            if (Objects.nonNull(task)) {
                final ExecutionCommand executionCommand = commandFactory.create(task.getTaskType(), task);
                log.info("Pushing execution command to Pending Command Queue");
                pendingCommandQueue.push(executionCommand);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
