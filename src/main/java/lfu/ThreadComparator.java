package lfu;

import java.util.Comparator;

public class ThreadComparator implements Comparator<WorkerThread> {
    @Override
    public int compare(WorkerThread o1, WorkerThread o2) {
        return o1.use-o2.use;
    }
}
