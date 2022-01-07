package tasks.datafiltering;

import tasks.Task;

public interface DataFilterTask extends Task {
    void loadData();
    void filterData();
}
