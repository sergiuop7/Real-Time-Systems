package Session4.Lab5.Lab7App4;

import java.util.concurrent.Semaphore;

public class Fir extends Thread {

    int delay, permit, activity_min, activity_max;
    Semaphore semaphore;

    Fir(Semaphore semaphore, int delay, int activity_min, int activity_max, int permit) {
        this.semaphore = semaphore;
        this.delay = delay;
        this.activity_max = activity_max;
        this.activity_min = activity_min;
        this.permit = permit;
    }

    public void run() {
        while(true) {
            try {
                System.out.println(this.getName() + " - STATE 1");

                this.semaphore.acquire(this.permit);

                System.out.println(this.getName() + " - STATE 2");

                int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k * 10000; i++) {
                    i++; i--;
                }
                this.semaphore.release(this.permit);

                System.out.println(this.getName() + " - STATE 3");
                Thread.sleep(this.delay * 500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(this.getName() + " - STATE 4");
        }
    }
}