package Session5.Lab6.Lab7App2.Semaphore;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class ExecutionThreadMiddle extends Thread {
    private final Semaphore semaphore1;
    private final Semaphore semaphore2;
    private final int sleep;
    private final int activity_min;
    private final int activity_max;
    private final CyclicBarrier cyclicBarrier;

    public ExecutionThreadMiddle(Semaphore semaphore1, Semaphore semaphore2, int sleep, int activity_min, int activity_max,
                           CyclicBarrier cyclicBarrier) {
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.cyclicBarrier = cyclicBarrier;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");

        try {
            semaphore1.acquire(1);
            semaphore2.acquire(1);

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore2.release(1);
        semaphore1.release(1);

        System.out.println(this.getName() + " - STATE 3");

        try {
            cyclicBarrier.await(); // Wait for other threads to reach the barrier
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}