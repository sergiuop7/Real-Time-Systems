package Session5.Lab6.Lab7App1.ReentrantLock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutionThread extends Thread {
    private final ReentrantLock lock1;
    private final ReentrantLock lock2;
    private final int sleep_min;
    private final int sleep_max;
    private final int sleep;
    private final int activity_min;
    private final int activity_max;
    private final CyclicBarrier cyclicBarrier;

    public ExecutionThread(ReentrantLock lock1, ReentrantLock lock2, int sleep_min, int sleep_max, int sleep,
                           int activity_min, int activity_max, CyclicBarrier cyclicBarrier) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.cyclicBarrier = cyclicBarrier;
    }

    public void run() {
        while (true) {
            System.out.println(this.getName() + " - STATE 1");

            try {
                Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock1.lock();
            try {
                System.out.println(this.getName() + " - STATE 2");
                int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }

                try {
                    cyclicBarrier.await(); // Wait for other threads to reach the barrier
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                lock2.lock();
                try {
                    System.out.println(this.getName() + " - STATE 3");
                    try {
                        Thread.sleep(Math.round(sleep) * 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock2.unlock();
                }
            } finally {
                lock1.unlock();
            }

            System.out.println(this.getName() + " - STATE 4");

            try {
                cyclicBarrier.await(); // Wait for other threads to reach the barrier
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
