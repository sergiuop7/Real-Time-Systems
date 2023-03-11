package Lab2.App2;

import java.util.ArrayList;

public class Main {

    private static final int noOfThreads=6;

    private static final int processorLoad=1000000;

    public static void main(String args[]){

        Model model = new Model(noOfThreads);

        View view = new View(model);

        Controller controller = new Controller(model, processorLoad);

        for(int i =0; i<noOfThreads; i++){

            controller.startFirThread(i,i+2);

        }

    }

}
