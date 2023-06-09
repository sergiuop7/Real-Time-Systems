package Session2.Lab3.App1;

public class Main {

    private static boolean stopThreads = false;

    private static Object lock = new Object();

    public static void main(String[] args){

        FileService service = new FileService("messages.txt");

        RThread reader = new RThread(service, lock);

        WThread writer = new WThread(service, lock);

        reader.start();

        writer.start();

    }

    public static boolean isStopThreads(){

        return stopThreads;

    }

}
