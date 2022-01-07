package taskresource.subscribers;

import tasks.Task;

public interface Subscriber extends Runnable {
    Task pull();
    void stop();
}
