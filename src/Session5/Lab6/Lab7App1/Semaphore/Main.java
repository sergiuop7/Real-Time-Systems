package Session5.Lab6.Lab7App1.Semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);

        while (true) {
            CountDownLatch countDownLatch = new CountDownLatch(3);

            new ExecutionThread(semaphore1, semaphore2, 2, 4, 4, 4, 6, countDownLatch).start();
            new ExecutionThread(semaphore2, semaphore1, 3, 5, 5, 5, 7, countDownLatch).start();

            countDownLatch.countDown();

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}