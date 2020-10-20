import java.util.*;
import javax.swing.*;
public class Driver {
    public static void main(String[] args){

        ArrayList<DataPoint> trainSet = new ArrayList<DataPoint>();
        trainSet.add(0, new DataPoint(1, 2, "red", "red"));
        trainSet.add(1, new DataPoint(2, 3, "blue", "blue"));
        trainSet.add(2, new DataPoint(3, 4, "red", "red"));
        trainSet.add(3, new DataPoint(4, 5, "blue", "blue"));

        ArrayList<DataPoint> testSet = new ArrayList<DataPoint>();
        testSet.add(0, new DataPoint(2.5, 3.5, "", ""));
        //trainSet.add(1, new DataPoint(1.1, 2, "", ""));

        DummyModel modelD = new DummyModel();
        modelD.train(trainSet);

        String result = modelD.test(testSet);
        JFrame pane = new JFrame("Headline");
        pane.setSize(100, 100);
        JLabel lblresult = new JLabel(result);
        pane.add(lblresult);
        pane.setVisible(true);
        pane.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
