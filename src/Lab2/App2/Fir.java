package Lab2.App2;

public class Fir extends Thread {

    private int id;
    private Model model;
    private int processorLoad;

    public Fir(int id, int priority, Model model, int processorLoad){
        this.id=id;
        this.model=model;
        this.processorLoad=processorLoad;
        this.setPriority(priority);
    }

    public void run(){

        int c=0;

        while(c<1000){

            for(int j=0;j<this.processorLoad;j++){
                j++;j--;
            }

            c++;
            this.model.setProgressValue(id, c);

        }
    }

}
