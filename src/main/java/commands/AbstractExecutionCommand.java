package commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import utils.CancellationToken;

@Getter
@AllArgsConstructor
public abstract class AbstractExecutionCommand implements ExecutionCommand {
    private final CancellationToken cancellationToken;

    public void stop() {
        cancellationToken.cancel();
    }
}
