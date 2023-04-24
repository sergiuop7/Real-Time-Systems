package Session3.Lab4.App3;

public class ExecutionThreadEx3 extends Thread {

    Integer monitor;

    int sleep, activity_min1,activity_max1;

    public ExecutionThreadEx3(Integer monitor, int sleep, int activity_min1, int activity_max1) {
        this.monitor = monitor;
        this.sleep = sleep;
        this.activity_min1 = activity_min1;
        this.activity_max1 = activity_max1;
    }

    public void run() {
        while (true) {
            System.out.println(this.getName() + " - STATE 1");
            synchronized (monitor) {
                System.out.println(this.getName() + " - STATE 2");
                int k = (int) Math.round(Math.random() * (activity_max1 - activity_min1) + activity_min1);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
            }
            System.out.println(this.getName() + " - STATE 3");
            try {
                Thread.sleep(sleep * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this.getName() + " - STATE 4");

        }
    }

}

