import lfu.LFU;
import lfu.WorkerThread;

public class MainClass {
    public static void main(String[] arg) throws InterruptedException {
        LFU lfu = new LFU(3);
        WorkerThread a = new WorkerThread("A");
        a.start();
        WorkerThread b = new WorkerThread("B");
        b.start();
        WorkerThread c = new WorkerThread("C");
        c.start();
        Thread.sleep(1000);
        lfu.put("C", c);
        lfu.put("A", a);
        lfu.put("B", b);
        Thread.sleep(1000);
        lfu.get("C");
        lfu.get("C");
        lfu.get("B");
        lfu.get("C");
        WorkerThread d = new WorkerThread("D");
        d.start();

        lfu.put("D",d);
        Thread.sleep(1000);
        lfu.get("B");
        lfu.get("D");
        lfu.get("C");

    }


}
