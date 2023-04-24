package Lab5.App3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerApp {

    private static final int MAX_CAPACITY = 100;
    private static final ArrayList<Integer> buffer = new ArrayList<>(MAX_CAPACITY);
    private static final Lock bufferLock = new ReentrantLock();
    private static final Condition bufferNotEmpty = bufferLock.newCondition();
    private static final Condition bufferNotFull = bufferLock.newCondition();

    public static void main(String[] args) {

        Thread producer = new Thread(() -> {
            while (true) {
                bufferLock.lock();
                try {
                    while (buffer.size() == MAX_CAPACITY) {
                        bufferNotFull.await();
                    }
                    Random random = new Random();
                    int num = random.nextInt(1000);
                    buffer.add(num);
                    System.out.println("Produced " + num);
                    bufferNotEmpty.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    bufferLock.unlock();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();

        Thread consumer1 = new Thread(() -> {
            while (true) {
                bufferLock.lock();
                try {
                    while (buffer.isEmpty()) {
                        bufferNotEmpty.await();
                    }
                    int num = buffer.remove(0);
                    System.out.println("Consumer1 consumed " + num);
                    bufferNotFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    bufferLock.unlock();
                }
            }
        });
        consumer1.start();

        Thread consumer2 = new Thread(() -> {
            while (true) {
                bufferLock.lock();
                try {
                    while (buffer.isEmpty()) {
                        bufferNotEmpty.await();
                    }
                    int num = buffer.remove(0);
                    System.out.println("Consumer2 consumed " + num);
                    bufferNotFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    bufferLock.unlock();
                }
            }
        });
        consumer2.start();

        Thread consumer3 = new Thread(() -> {
            while (true) {
                bufferLock.lock();
                try {
                    while (buffer.isEmpty()) {
                        bufferNotEmpty.await();
                    }
                    int num = buffer.remove(0);
                    System.out.println("Consumer3 consumed " + num);
                    bufferNotFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    bufferLock.unlock();
                }
            }
        });
        consumer3.start();
    }
}
