package lfu;

public class WorkerThread extends Thread{
    int use = 0;
    String key;
    boolean stop = true;

    public WorkerThread(String key) {
        this.key = key;
    }

    @Override
    public void run() {
        while(stop){
            try {
                Thread.sleep(1000);
                System.out.println(key);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return ""+key+" "+use;
    }
}
