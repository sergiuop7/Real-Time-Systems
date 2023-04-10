package Lab4.App4;

public class Main
{
    public static void main(String[] args) {

        Integer monitor1 = new Integer(1);
        Integer monitor2 = new Integer(2);
        ExecutionThreadEx4 t1 = new ExecutionThreadEx4(monitor1, monitor2, 7, 2, 3, null);
        ExecutionThreadEx4 t2 = new ExecutionThreadEx4(monitor1, monitor2, 0, 3, 5, t1);
        ExecutionThreadEx4 t3 = new ExecutionThreadEx4(monitor1, monitor2, 0, 4, 6, t1);

        t1.start();
        t2.start();
        t3.start();

    }
}