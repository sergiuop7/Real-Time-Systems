package Session5.Lab6.Lab7App2.Semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);

        while (true) {
            CountDownLatch countDownLatch = new CountDownLatch(3);

            new ExecutionThread(semaphore1, 2, 4, 4, countDownLatch).start();
            new ExecutionThread(semaphore2, 3, 6, 3, countDownLatch).start();
            new ExecutionThread(semaphore2, 2, 5, 5, countDownLatch).start();

            countDownLatch.countDown();

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
