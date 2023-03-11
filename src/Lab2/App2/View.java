package Lab2.App2;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class View implements Observer {

    private JFrame frame;
    private ArrayList<JProgressBar> bars=new ArrayList<>();

    public View(Model model) {
        model.addObserver(this);

        frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(450,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init(model.getProgressValues().size());

        frame.setVisible(true);
    }

    private void init(int n){

        for(int i=0 ;i<n; i++){

            JProgressBar pb=new JProgressBar();
            pb.setMaximum(1000);
            pb.setBounds(50,(i+1)*30,350,20);
            this.bars.add(pb);
            frame.add(pb);

        }

    }

    @Override
    public void update(Observable o, Object arg) {
        Model model = (Model) o;
        ArrayList<Integer> progressValues = model.getProgressValues();
        for (int i = 0; i < progressValues.size(); i++) {
            bars.get(i).setValue(progressValues.get(i));
        }
    }

}
