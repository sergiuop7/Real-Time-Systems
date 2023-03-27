package Lab3.Example3;

public class Main {
    public static void main(String[] args) {
        DividerThread t1 = new DividerThread("Thread 1", 1, 50000);
        DividerThread t2 = new DividerThread("Thread 2", 1, 20000);

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final sum: " + DividerThread.getSum());
    }
}