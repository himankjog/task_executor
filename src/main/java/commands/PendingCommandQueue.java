package commands;

import lombok.extern.log4j.Log4j2;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
public class PendingCommandQueue<C extends ExecutionCommand> {
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition TASK_QUEUE_EMPTY = LOCK.newCondition();
    private volatile static PendingCommandQueue<ExecutionCommand> instance;
    private final Queue<C> pendingCommandQueue;

    private PendingCommandQueue() {
        pendingCommandQueue = new LinkedList<>();
    }

    public static PendingCommandQueue<ExecutionCommand> getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (PendingCommandQueue.class) {
                if (Objects.isNull(instance)) {
                    instance = new PendingCommandQueue<>();
                }
            }
        }
        return instance;
    }

    public void push(final C command) {
        LOCK.lock();
        try{
            pendingCommandQueue.add(command);
            TASK_QUEUE_EMPTY.signalAll();
        } catch (Exception ex) {
            log.error("Some issue while adding task: {} to the queue.", command);
        } finally {
            LOCK.unlock();
        }
    }

    public C get() throws InterruptedException {
        LOCK.lock();
        try {
            while (pendingCommandQueue.isEmpty()) {
                TASK_QUEUE_EMPTY.await();
            }
            return pendingCommandQueue.remove();
        } finally {
            LOCK.unlock();
        }
    }
}
