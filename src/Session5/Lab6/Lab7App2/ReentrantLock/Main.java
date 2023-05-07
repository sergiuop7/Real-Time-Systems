package Session5.Lab6.Lab7App2.ReentrantLock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        new ExecutionThread(lock1, 2, 4, 4, cyclicBarrier).start();
        new ExecutionThreadMiddle(lock1, lock2, 3, 6, 3, cyclicBarrier).start();
        new ExecutionThread(lock2, 2, 5, 5, cyclicBarrier).start();
    }
}