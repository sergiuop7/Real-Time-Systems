package Session2.Lab3.App1;

public class WThread extends Thread{

    private FileService service;

    private Object lock;

    public WThread(FileService service, Object lock) {

        this.service = service;

        this.lock = lock;

    }

    public void run(){

        while(!Main.isStopThreads()){

            String msg=

                    String.valueOf(Math.round(Math.random()*100));

            synchronized(lock) {
                service.write(msg);
            }

            try {

                Thread.sleep(2000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

}
