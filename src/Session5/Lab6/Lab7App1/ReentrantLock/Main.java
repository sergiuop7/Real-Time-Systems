package Session5.Lab6.Lab7App1.ReentrantLock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        while (true) {
            new ExecutionThread(lock1, lock2, 2, 4, 4, 4, 6, cyclicBarrier).start();
            new ExecutionThread(lock2, lock1, 3, 5, 5, 5, 7, cyclicBarrier).start();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.getMessage();
            }
            cyclicBarrier.reset();
        }
    }
}