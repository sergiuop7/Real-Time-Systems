package Lab3.Example5;

public class RThread extends Thread{

    private FileService service;

    private Object lock;

    public RThread(FileService service, Object lock) {

        this.service = service;

        this.lock = lock;

    }

    public void run(){

        while (!Main.isStopThreads()){

            try {

                String readMsg;
                synchronized(lock) {
                    readMsg = service.read();
                }
                System.out.println(readMsg);

                Thread.sleep(3000);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

}