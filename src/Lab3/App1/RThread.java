package Lab3.App1;

public class RThread extends Thread {
    FileService service;
    Object readLock;

    public RThread(FileService service) {
        this.service = service;
        this.readLock = service.readLock;
    }

    public void run() {
        while (!Main.isStopThreads()) {
            try {
                synchronized (readLock) {
                    String readMsg = service.read();
                    System.out.println(readMsg);
                }
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}