package Session5.Lab6.Lab7App4;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        new Fir(semaphore, 3, 4, 7, 2).start();
        new Fir(semaphore, 6, 5, 7, 2).start();
        new Fir(semaphore, 5, 3, 6, 2).start();
    }
}