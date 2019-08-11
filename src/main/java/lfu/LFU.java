package lfu;

import java.nio.charset.IllegalCharsetNameException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LFU<E,V extends WorkerThread> {
    private Map<E, V> map;
    private int size;
    private PriorityQueue<V> pq = new PriorityQueue<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock =readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public LFU(int size) {
        this.size = size;
        map = new HashMap<>(size);
    }

    public void put(E key, V workerThread) throws InterruptedException {
        if (map.containsKey(key)) {
            throw new IllegalCharsetNameException("");
        } else {
            writeLock.lock();
            Thread.sleep(200);
            if (map.size() == size) {
                V thread = pq.poll();
                assert thread != null;
                map.remove(thread.key);
                System.out.println(thread.key +" removed!!!");
                thread.stop = false;
            }
            pq.add(workerThread);
            map.put(key, workerThread);
            System.out.println(pq.toString());
            writeLock.unlock();
        }
    }

    public void get(E key) throws InterruptedException {
        readLock.lock();
        Thread.sleep(200);
        if (map.containsKey(key)) {
            map.get(key).use++;
            System.out.println("Found "+key);
            readLock.unlock();
            System.out.println(pq.toString());
            return;
        }
        System.out.println("Not found!! "+key);
        readLock.unlock();
    }


}
