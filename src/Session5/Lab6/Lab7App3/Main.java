package Session5.Lab6.Lab7App3;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        Object monitor1 = new Object();
        Object monitor2 = new Object();
        CountDownLatch countDownLatch = new CountDownLatch(4);

        while (true) {
            ExecutionThread1 thread1 = new ExecutionThread1(monitor1, monitor2, 7, 2, 3, countDownLatch);
            ExecutionThread2 thread2 = new ExecutionThread2(thread1, monitor1, 3, 5, 1, countDownLatch);
            ExecutionThread2 thread3 = new ExecutionThread2(thread1, monitor2, 4, 6, 2, countDownLatch);

            thread1.start();
            thread2.start();
            thread3.start();

            countDownLatch.countDown();

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
