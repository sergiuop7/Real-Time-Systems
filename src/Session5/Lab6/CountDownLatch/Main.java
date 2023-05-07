package Session5.Lab6.CountDownLatch;

import java.util.concurrent.CountDownLatch;

    public class Main {
        public static void main(String args[]) {
            CountDownLatch countDownLatch = new CountDownLatch(3);

            Fir fir1 = new Fir(countDownLatch, "fir 1");
            Fir fir2 = new Fir(countDownLatch, "fir 2");
            Fir fir3 = new Fir(countDownLatch, "fir 3");
            fir1.run();
            fir2.run();
            fir3.run();
        }
    }
