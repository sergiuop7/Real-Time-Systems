package Session3.Lab4.App1;

public class Main {
    public static void main(String[] args) {
        Integer monitor1 = new Integer(1);
        Integer monitor2 = new Integer(2);
        new ExecutionThreadEx1(monitor1,4,2,4).start();
        new ExecutionThreadMiddle(monitor1,monitor2,3,3,6).start();
        new ExecutionThreadEx1(monitor2,5,2,5).start();
    }
}
