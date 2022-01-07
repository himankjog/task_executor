package taskresource;

import commands.ExecutionCommand;
import commands.PendingCommandQueue;
import lombok.extern.log4j.Log4j2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
public class TaskExecutor {
    private final ExecutorService threadPool;
    private final PendingCommandQueue<ExecutionCommand> pendingCommandQueue;
    private final Queue<ExecutionCommand> executionCommandBacklog;

    public TaskExecutor() {
        threadPool = Executors.newFixedThreadPool(20);
        pendingCommandQueue = PendingCommandQueue.getInstance();
        executionCommandBacklog = new LinkedList<>();
    }

    public void start() throws InterruptedException {
        int iterations = 0;
        while(true) {

            if (iterations == 3) {
                threadPool.shutdownNow();
                break;
            }
            executionCommandBacklog.add(pendingCommandQueue.get());

            if (executionCommandBacklog.size() >= 10) {
               for (int i = 0; i < executionCommandBacklog.size(); ++i) {
                   threadPool.submit(executionCommandBacklog.remove());
               }
               ++iterations;
               log.info("Submitted commands to thread pool");
            }
        }
    }

}
