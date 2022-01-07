package taskresource.publishers;

import tasks.Task;

public interface Publisher extends Runnable {
    void publish(Task task);
    void stop();
}
