package Session5.Lab6.App2;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            Fir fir = new Fir(fileWriter);
            fir.startSimulation();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
