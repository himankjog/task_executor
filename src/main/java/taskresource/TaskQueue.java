package taskresource;

import enums.PublishStates;
import lombok.extern.log4j.Log4j2;
import tasks.Task;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
public class TaskQueue<T extends Task> {
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition TASK_QUEUE_EMPTY = LOCK.newCondition();
    private volatile static TaskQueue<Task> instance;
    private final Queue<T> taskQueue;

    private TaskQueue() {
        taskQueue = new LinkedList<>();
    }

    /**
     * Making sure that there is only 1 instance of the queue.
     * */
    public static TaskQueue<Task> getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (TaskQueue.class) {
                if (Objects.isNull(instance)) {
                    instance = new TaskQueue<>();
                }
            }
        }
        return instance;
    }

    public PublishStates push(final T task) {
        LOCK.lock();
        try{
            taskQueue.add(task);
            TASK_QUEUE_EMPTY.signalAll();
        } catch (Exception ex) {
            log.error("Some issue while adding task: {} to the queue. Ex: {}", task.getTaskId(), ex);
            return PublishStates.NOT_PUBLISHED;
        } finally {
            LOCK.unlock();
        }
        return PublishStates.PUBLISHED;
    }

    public T get() throws InterruptedException {
        LOCK.lock();
        try {
            while (taskQueue.isEmpty()) {
                TASK_QUEUE_EMPTY.await();
            }
            return taskQueue.remove();
        } finally {
            LOCK.unlock();
        }
    }
}
