package Lab3.App1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class FileService {
    String fileName;
    BufferedReader in;
    PrintWriter out;
    Object writeLock = new Object();
    Object readLock = new Object();

    public FileService(String fname){
        this.fileName = fname;
        try {
            out = new PrintWriter(new FileWriter(fileName, true));
            in = new BufferedReader(new FileReader(fileName));
        } catch (Exception e) { e.printStackTrace();}
    }

    public void write(String msg){
        Date date = new Date(System.currentTimeMillis());
        synchronized (writeLock) {
            out.println("Date: " + date);
            out.println("Message: " + msg);
            out.flush();
        }
    }

    public String read() throws IOException{
        String iterator, last="no message to read";
        synchronized (readLock) {
            while((iterator = in.readLine()) != null){
                last= new Date(System.currentTimeMillis()) + " - " + iterator;
            }
        }
        return last;
    }
}
