package Lab4.App1;

public class Main {
    public static void main(String[] args) {

        Integer monitor1 = new Integer(1);
        Integer monitor2 = new Integer(2);
        new ExecutionThreadEx1(monitor1, monitor2,2, 4, 4).start();
        new ExecutionThreadEx1(monitor1, monitor2,3, 6, 3).start();
        new ExecutionThreadEx1(monitor1, monitor2,2, 5, 5).start();

    }

}