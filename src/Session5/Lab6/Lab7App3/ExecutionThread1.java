package Session5.Lab6.Lab7App3;

import java.util.concurrent.CountDownLatch;

public class ExecutionThread1 extends Thread {
    private final Object monitor1;
    private final Object monitor2;
    private final int sleep;
    private final int activity_min;
    private final int activity_max;
    private CountDownLatch countDownLatch;

    public ExecutionThread1(Object monitor1, Object monitor2, int sleep, int activity_min, int activity_max,
                            CountDownLatch countDownLatch) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");

        try {
            Thread.sleep(Math.round(Math.random() * sleep) * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.getName() + " - STATE 2");

        int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        synchronized (monitor1) {
            monitor1.notify();
        }

        synchronized (monitor2) {
            monitor2.notify();
        }

        System.out.println(this.getName() + " - STATE 4");

        countDownLatch.countDown();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
