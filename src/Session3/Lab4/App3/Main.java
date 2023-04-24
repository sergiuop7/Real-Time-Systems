package Session3.Lab4.App3;

public class Main
{
    public static void main(String[] args) {

        Integer monitor = new Integer(1);
        new ExecutionThreadEx3(monitor,3, 4, 7).start();
        new ExecutionThreadEx3(monitor,5, 3, 6).start();
        new ExecutionThreadEx3(monitor,6, 5, 7).start();

    }
}