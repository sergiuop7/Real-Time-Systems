package Session5.Lab6.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Fir {
    CountDownLatch countDownLatch;
    String name;

    public Fir(CountDownLatch countDownLatch, String name) {
        this.countDownLatch = countDownLatch;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void run() {
        while (true) {
            activitate1();

            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            activitate2();
        }
    }

    public void activitate1() {
        System.out.println(this.getName() + "> activitate 1");
        try {
            Thread.sleep(Math.round(Math.random() * 3 + 3) * 1000);
        } catch (InterruptedException e) {
        }
    }

    public void activitate2() {
        System.out.println(this.getName() + "> activitate 2");
        try {
            Thread.sleep(Math.round(Math.random() * 3 + 3) * 1000);
        } catch (InterruptedException e) {
        }
    }
}
