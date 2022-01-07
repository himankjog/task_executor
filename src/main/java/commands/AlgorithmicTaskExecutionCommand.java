package commands;

import lombok.extern.log4j.Log4j2;
import tasks.algorithmic.AlgorithmicTask;
import utils.CancellationToken;

@Log4j2
public class AlgorithmicTaskExecutionCommand extends AbstractExecutionCommand {
    private final AlgorithmicTask algorithmicTask;

    public AlgorithmicTaskExecutionCommand(final AlgorithmicTask algorithmicTask) {
        super(new CancellationToken());
        this.algorithmicTask = algorithmicTask;
    }

    @Override
    public void execute() {
        setupHardware();
        algorithmicTask.execute();
    }

    private void setupHardware() {
        log.info("Setting up GPUs before running for {}.", algorithmicTask.getClass().getName());
    }

    @Override
    public void run() {
        if (!getCancellationToken().isCancelled() && !Thread.currentThread().isInterrupted()) {
            execute();

            log.info("Completed execution of {}", algorithmicTask.getClass().getName());
        }
    }
}
