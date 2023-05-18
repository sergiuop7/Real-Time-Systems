package Session5.Lab6.App2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Fir {
    private static final int NUM_ITERATIONS = 100;
    private static final int THREADS_COUNT = 3;
    private static final int RANDOM_MIN = -10;
    private static final int RANDOM_MAX = 10;

    private volatile int iterationCount = 0;
    private final CyclicBarrier barrier;
    private final FileWriter fileWriter;

    public Fir(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
        barrier = new CyclicBarrier(THREADS_COUNT, this::checkSum);
    }

    public void startSimulation() {
        Thread[] threads = new Thread[THREADS_COUNT];

        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new MathWorker(), "Thread " + (i + 1));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class MathWorker implements Runnable {
        private final Random random;

        public MathWorker() {
            random = new Random();
        }

        @Override
        public void run() {
            try {
                while (iterationCount < NUM_ITERATIONS) {
                    int randomNumber = random.nextInt(RANDOM_MAX - RANDOM_MIN + 1) + RANDOM_MIN;
                    synchronized (fileWriter) {
                        fileWriter.write("Iteration: " + (iterationCount + 1) + ", " +
                                Thread.currentThread().getName() + " Result: " + randomNumber + "\n");
                        fileWriter.flush();
                    }

                    barrier.await(); // Wait for other threads to finish this iteration
                }
            } catch (InterruptedException | BrokenBarrierException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkSum() {
        synchronized (fileWriter) {
            iterationCount++;

            if (iterationCount > NUM_ITERATIONS) {
                return;
            }

            if (iterationCount >= 1) {
                // Write iteration header to file
                try {
                    fileWriter.write("Iteration: " + iterationCount + "\n");
                    fileWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (iterationCount % THREADS_COUNT == 0) {
                // Calculate sum and check if it equals 0
                int sum = 0;
                try {
                    String[] lines = FileHelper.readLines("output.txt", THREADS_COUNT);

                    for (int i = 0; i < THREADS_COUNT; i++) {
                        String line = lines[iterationCount - i - 1];
                        String[] parts = line.split(": ");

                        for (int j = 0; j < parts.length; j++) {
                            int result = Integer.parseInt(parts[j]);
                            sum += result;
                        }
                    }

                    if (sum == 0) {
                        System.out.println("Number of iterations performed by each thread: " + iterationCount);
                    } else {
                        fileWriter.write("Iteration: " + iterationCount + "\n");
                        fileWriter.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
