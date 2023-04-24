package Lab5.Lab4App2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        new ExecutionThread(lock1, lock2, 2, 4, 4, 4, 6).start();
        new ExecutionThread(lock2, lock1, 3, 5, 5, 5, 7).start();
    }
}