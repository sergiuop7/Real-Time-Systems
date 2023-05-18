package Session5.Lab6.App2;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MathProblemSimulation {
    private static final int NUM_ITERATIONS = 100;
    private static final int THREADS_COUNT = 3;
    private static final int RANDOM_MIN = -10;
    private static final int RANDOM_MAX = 10;

    private static volatile int iterationCount = 0;
    private static CyclicBarrier barrier;
    private static FileWriter fileWriter;

    public static void main(String[] args) {
        try {
            fileWriter = new FileWriter("output.txt");
            barrier = new CyclicBarrier(THREADS_COUNT, MathProblemSimulation::checkSum);
            Thread[] threads = new Thread[THREADS_COUNT];

            for (int i = 0; i < THREADS_COUNT; i++) {
                threads[i] = new Thread(new MathWorker(), "Thread " + (i + 1));
                threads[i].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

            fileWriter.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class MathWorker implements Runnable {
        private Random random;

        public MathWorker() {
            random = new Random();
        }

        @Override
        public void run() {
            try {
                while (iterationCount < NUM_ITERATIONS) {
                    int randomNumber = random.nextInt(RANDOM_MAX - RANDOM_MIN + 1) + RANDOM_MIN;
                    fileWriter.write("Iteration: " + (iterationCount + 1) + ", " +
                            Thread.currentThread().getName() + " Result: " + randomNumber + "\n");
                    fileWriter.flush();

                    barrier.await(); // Wait for other threads to finish this iteration
                }

                barrier.reset();
            } catch (InterruptedException | BrokenBarrierException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkSum() {
        iterationCount++;

        if (iterationCount > NUM_ITERATIONS) {
            return;
        }

        if (iterationCount == 1) {
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
                for (int i = 1; i <= THREADS_COUNT; i++) {
                    String line = Files.readAllLines(Paths.get("output.txt")).get(iterationCount - i);
                    String[] parts = line.split(": ");
                    int result = Integer.parseInt(parts[1].trim());
                    sum += result;
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