package Session5.Lab6.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Fir extends Thread {
    CyclicBarrier bariera;
    public Fir(CyclicBarrier bariera) {
        this.bariera = bariera;
    }
    public void run() {
        while (true) {
            activitate();
            try {
                bariera.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            activitate();
        }
    }
    public void activitate() {
        System.out.println(this.getName() + "> activitate");
        try {
            Thread.sleep(Math.round(Math.random() * 3 + 3) * 1000);
        } catch (InterruptedException e) {
        }
    }
}
public class Main {
    public static void main(String args[]) {
        CyclicBarrier bariera = new CyclicBarrier(3, new Runnable() {
            public void run() {
                System.out.println("Barrier Rutine");
            }
        });
        Fir fir1 = new Fir(bariera);
        Fir fir2 = new Fir(bariera);
        Fir fir3 = new Fir(bariera);
        fir1.start();
        fir2.start();
        fir3.start();
    }
}
