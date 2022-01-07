package commands;

import lombok.extern.log4j.Log4j2;
import tasks.datafiltering.DataFilterTask;
import utils.CancellationToken;

@Log4j2
public class DataFilteringTaskExecutionCommand extends AbstractExecutionCommand {
    private final DataFilterTask dataFilterTask;

    public DataFilteringTaskExecutionCommand(final DataFilterTask dataFilterTask) {
        super(new CancellationToken());
        this.dataFilterTask = dataFilterTask;
    }

    @Override
    public void execute() {
        dataValidation();
        dataFilterTask.execute();
    }

    private void dataValidation() {
        log.info("Doing some data validation for {}", dataFilterTask.getClass().getName());
    }

    @Override
    public void run() {
        if (!getCancellationToken().isCancelled() && !Thread.currentThread().isInterrupted()) {
            execute();

            log.info("Completed execution of {}. TaskId: {}", dataFilterTask.getClass().getName(), dataFilterTask.getTaskId());
        }
    }
}
