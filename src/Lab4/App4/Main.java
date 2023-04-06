package Lab4.App4;

public class Main
{
    public static void main(String[] args) {

        Integer monitor1 = new Integer(1);
        Integer monitor2 = new Integer(2);
        new ExecutionThreadEx4(monitor1, monitor2, 7, 2, 3).start();
        new ExecutionThreadEx4(monitor1, monitor2, 0, 3, 5).start();
        new ExecutionThreadEx4(monitor1, monitor2, 0, 4, 6).start();

    }
}