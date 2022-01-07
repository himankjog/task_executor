import taskresource.TaskExecutor;
import taskresource.publishers.AlgorithmicTaskPublisher;
import taskresource.publishers.DataFilteringTaskPublisher;
import taskresource.publishers.Publisher;
import taskresource.subscribers.Subscriber;
import taskresource.subscribers.TaskSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutorApplication {
    public static void main(String[] args) throws InterruptedException {

        final ExecutorService threadpool = Executors.newFixedThreadPool(5);
        final List<Publisher> publisherList = new ArrayList<>();
        publisherList.add(new AlgorithmicTaskPublisher());
        publisherList.add(new DataFilteringTaskPublisher());
        publisherList.add(new DataFilteringTaskPublisher());

        final List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(new TaskSubscriber());
        subscribers.add(new TaskSubscriber());

        for (Publisher publisher: publisherList) {
            threadpool.submit(publisher);
        }

        for (Subscriber subscriber: subscribers) {
            threadpool.submit(subscriber);
        }

        final TaskExecutor taskExecutor = new TaskExecutor();

        taskExecutor.start();

        threadpool.shutdownNow();
    }
}
