package Lab2.App2;

public class Controller {

    private Model model;
    private int processorLoad;

    public Controller(Model model, int processorLoad) {
        this.model = model;
        this.processorLoad = processorLoad;
    }

    public void startFirThread(int id,int priority){
        Fir fir = new Fir(id, priority, model, processorLoad);
        fir.start();
    }

}