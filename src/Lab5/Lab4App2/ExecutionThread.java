package Lab5.Lab4App2;

import java.util.concurrent.locks.ReentrantLock;

public class ExecutionThread extends Thread {

    ReentrantLock lock1, lock2;
    int sleep_min, sleep_max, sleep, activity_min, activity_max;
    public ExecutionThread(ReentrantLock lock1, ReentrantLock lock2, int sleep_min, int sleep_max, int sleep, int activity_min, int activity_max) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");

        if (lock1.tryLock()) {
            System.out.println(this.getName() + " - STATE 2");
            int k = (int) Math.round(Math.random()*(activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++; i--;
            }

            if (lock2.tryLock()) {
                System.out.println(this.getName() + " - STATE 3");
                try {
                    Thread.sleep(Math.round(sleep) * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock2.unlock();
                }
            } else {
                lock1.unlock();
            }
        }

        System.out.println(this.getName() + " - STATE 4");
    }
}
