package Lab4.App4;

public class ExecutionThreadEx4 extends Thread {

    Integer monitor1, monitor2;

    int sleep, activity_min,activity_max;

    public ExecutionThreadEx4(Integer monitor1,Integer monitor2, int sleep, int activity_min, int activity_max) {
        this.monitor1 = monitor1;
        this.monitor2=monitor2;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");

        switch(this.getName()){
            case "Thread-0":

                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + " - STATE 2");

                int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                synchronized (monitor1) {
                    synchronized (monitor2) {
                        monitor1.notify();
                        monitor2.notify();
                    }
                }

                System.out.println(this.getName() + " - STATE 3");
                break;

            case "Thread-1":
                synchronized (monitor1) {
                    try {
                        monitor1.wait(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(this.getName() + " - STATE 2");
                    int k1 = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                    for (int i = 0; i < k1 * 100000; i++) {
                        i++;
                        i--;
                    }

                    System.out.println(this.getName() + " - STATE 3");
                }
                break;

            case "Thread-2":
                synchronized (monitor2){
                    try {
                        monitor2.wait(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(this.getName() + " - STATE 2");
                    int k2 = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                    for (int i = 0; i < k2 * 100000; i++) {
                        i++;
                        i--;
                    }

                    System.out.println(this.getName() + " - STATE 3");
                }
                break;

            default:
                break;
        }

    }

}

