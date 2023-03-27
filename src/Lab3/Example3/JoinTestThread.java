package Lab3.Example3;

class DividerThread extends Thread {
    private int start;
    private int end;
    private static int sum = 0;

    public DividerThread(String name, int start, int end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public void run() {
        System.out.println(getName() + " has entered the run() method.");
        int localSum = 0;
        for (int i = start; i <= end; i++) {
            if (i == 0) continue;
            if (end % i == 0) localSum += i;
        }
        System.out.println(getName() + " has determined the dividers. Local sum: " + localSum);

        synchronized (DividerThread.class) {
            sum += localSum;
            System.out.println(getName() + " has updated the sum to: " + sum);
        }

        System.out.println(getName() + " has terminated.");
    }

    public static int getSum() {
        return sum;
    }
}




