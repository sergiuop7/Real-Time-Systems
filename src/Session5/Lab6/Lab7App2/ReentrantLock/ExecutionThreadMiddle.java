package Session5.Lab6.Lab7App2.ReentrantLock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutionThreadMiddle extends Thread {
    private final ReentrantLock lock1;
    private final ReentrantLock lock2;
    private final int sleep;
    private final int activity_min;
    private final int activity_max;
    private final CyclicBarrier cyclicBarrier;

    public ExecutionThreadMiddle(ReentrantLock lock1, ReentrantLock lock2, int sleep, int activity_min, int activity_max,
                            CyclicBarrier cyclicBarrier) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.cyclicBarrier = cyclicBarrier;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");

        lock1.lock();
        lock2.lock();

        System.out.println(this.getName() + " - STATE 2");
        int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        try {
            Thread.sleep(Math.round(sleep) * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock1.unlock();
        lock2.unlock();

        System.out.println(this.getName() + " - STATE 3");

        try {
            cyclicBarrier.await(); // Wait for other threads to reach the barrier
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}