package factories.commandfactory;

import commands.ExecutionCommand;
import enums.TaskType;
import factories.Factory;
import lombok.extern.log4j.Log4j2;
import tasks.Task;


@Log4j2
public abstract class AbstractCommandFactory<P extends ExecutionCommand, E extends TaskType, T extends Task>  implements Factory<P,E,T>  {
    @Override
    public P create(final E productEnum, final T... optionalInputs) {
        T firstInput = null;
        if (optionalInputs.length > 0) firstInput = optionalInputs[0];
        final P executionCommand = createExecutionCommand(productEnum, firstInput);
        log.info("Got execution command for {}", productEnum.name());
        return executionCommand;
    }

    protected abstract P createExecutionCommand(E productEnum, T optionalTask);
}
