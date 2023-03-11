package Lab2.App2;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

    private ArrayList<Integer> progressValues = new ArrayList<>();

    public Model(int nrThreads) {
        for(int i=0; i<nrThreads; i++){
            progressValues.add(0);
        }
    }

    public void setProgressValue(int id,int val){
        progressValues.set(id, val);
        setChanged();
        notifyObservers();
    }

    public ArrayList<Integer> getProgressValues() {
        return progressValues;
    }

}