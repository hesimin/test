import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadLock {
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) ;
            }
        }, "testBusyThread");
        thread.start();
    }

    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1:"+br.readLine());
        createBusyThread();
        System.out.println("2:"+br.readLine());
        Object o = new Object();
        createLockThread(o);
        System.out.println("3:"+br.readLine());
        synchronized (o) {
            o.notifyAll();
        }
    }
}
