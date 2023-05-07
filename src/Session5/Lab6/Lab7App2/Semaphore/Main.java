package Session5.Lab6.Lab7App2.Semaphore;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        new ExecutionThread(semaphore1, 2, 4, 4, cyclicBarrier).start();
        new ExecutionThread(semaphore2, 3, 6, 3, cyclicBarrier).start();
        new ExecutionThread(semaphore2, 2, 5, 5, cyclicBarrier).start();
    }
}
