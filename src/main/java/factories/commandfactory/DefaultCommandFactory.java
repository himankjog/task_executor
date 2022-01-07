package factories.commandfactory;

import commands.AlgorithmicTaskExecutionCommand;
import commands.DataFilteringTaskExecutionCommand;
import commands.ExecutionCommand;
import commands.GatherDataTaskExecutionCommand;
import enums.TaskType;
import tasks.Task;
import tasks.algorithmic.AlgorithmicTask;
import tasks.datafiltering.DataFilterTask;
import tasks.datagathering.GatherDataTask;

public class DefaultCommandFactory extends AbstractCommandFactory<ExecutionCommand, TaskType, Task> {

    @Override
    protected ExecutionCommand createExecutionCommand(final TaskType productEnum, final Task task) {
        ExecutionCommand executionCommand = null;
        if (productEnum == TaskType.ALGORITHMIC_TASK) {
            final AlgorithmicTask algorithmicTask = (AlgorithmicTask) task;
            executionCommand = new AlgorithmicTaskExecutionCommand(algorithmicTask);
        } else if (productEnum == TaskType.DATA_FILTER_TASK) {
            final DataFilterTask dataFilterTask = (DataFilterTask) task;
            executionCommand = new DataFilteringTaskExecutionCommand(dataFilterTask);
        } else if (productEnum == TaskType.GATHER_DATA_TASK) {
            final GatherDataTask gatherDataTask = (GatherDataTask) task;
            executionCommand = new GatherDataTaskExecutionCommand(gatherDataTask);
        }
        return executionCommand;
    }
}
