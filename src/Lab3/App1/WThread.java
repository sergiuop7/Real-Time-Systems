package Lab3.App1;

public class WThread extends Thread{
    FileService service;
    Object writeLock;
    public WThread(FileService service) {
        this.service = service;
        this.writeLock = service.writeLock;
    }
    public void run(){
        while(!Main.isStopThreads()){
            String msg=
                    String.valueOf(Math.round(Math.random()*100));
            synchronized (writeLock) {
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
