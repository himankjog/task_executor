package commands;

import lombok.extern.log4j.Log4j2;
import tasks.datagathering.GatherDataTask;
import utils.CancellationToken;

@Log4j2
public class GatherDataTaskExecutionCommand extends AbstractExecutionCommand {
    private final GatherDataTask gatherDataTask;

    public GatherDataTaskExecutionCommand(final GatherDataTask gatherDataTask) {
        super(new CancellationToken());
        this.gatherDataTask = gatherDataTask;
    }

    @Override
    public void execute() {
        preRequisiteStuff();
        log.info("Done with pre-requisite stuff");
        gatherDataTask.execute();
        postGatherDataTaskCompletion();
    }

    private void postGatherDataTaskCompletion() {
        log.info("Doing some post {} work", gatherDataTask.getClass().getName());
    }

    private void preRequisiteStuff() {
        log.info("Doing some pre-requisite stuff before executing {}.", gatherDataTask.getClass().getName());
    }

    @Override
    public void run() {
        if (!getCancellationToken().isCancelled() && !Thread.currentThread().isInterrupted()) {
            execute();

            log.info("Completed execution of {}", gatherDataTask.getClass().getName());
        }
    }
}
