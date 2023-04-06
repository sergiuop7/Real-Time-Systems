package Lab4.App1;

public class ExecutionThreadEx1 extends Thread {
    Integer monitor1, monitor2;
    int sleep_min, sleep_max, sleep_s;

    public ExecutionThreadEx1(Integer monitor1, Integer monitor2, int sleep_min, int sleep_max, int sleep_s) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.sleep_s = sleep_s;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        try {
            switch (this.getName()) {
                case "Thread-0":
                    synchronized (monitor1) {
                        synchronized (monitor2) {
                            System.out.println(this.getName()+" - STATE 2");
                            int k = (int) Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min);
                            for (int i = 0; i < k * 100000; i++) {
                                i++;
                                i--;
                            }
                            try {
                                Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
                            } catch (InterruptedException e) {

                            }
                            System.out.println(this.getName()+" - STATE 3");
                        }
                    }
                    break;
                default:
                    synchronized (monitor1)
                    {
                        System.out.println(this.getName()+" - STATE 2");
                        int k1 = (int) Math.round(Math.random() * ((sleep_min- sleep_max)+sleep_min));
                        for (int i = 0; i < k1 * 100000; i++) {
                            i++;
                            i--;
                        }
                        try {
                            Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
                        } catch (InterruptedException e) {

                        }
                        System.out.println(this.getName()+" - STATE 3");
                    }

            }
            Thread.sleep(Math.round(Math.random() * sleep_s * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
