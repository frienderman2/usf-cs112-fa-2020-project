import java.util.*;
public class DummyModel extends Model{

    private double avgF1 = 0;
    private double avgF2 = 0;

    void train(ArrayList<DataPoint> data) {
        double avgF1 = 0;
        double avgF2 = 0;
        int num1 = 0;
        int num2 = 0;

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getLabel() == "red") {
                avgF1 += data.get(i).getf1();
                num1 += 1;
            }

            if (data.get(i).getLabel() == "blue") {
                avgF2 += data.get(i).getf2();
                num2 += 1;
            }
        }
        this.avgF1 = avgF1 / num1;
        this.avgF2 = avgF2 / num2;
    }


    String test(ArrayList<DataPoint> data) {
        DataPoint testData = data.get(0);

        if (Math.abs(testData.getf1() - this.avgF1) > Math.abs(testData.getf1() - this.avgF2)) {
            return "blue";
        }
        else {
            return "red";
        }
    }

    Double getAccuracy(ArrayList<DataPoint> data) {
        return null;
    }

    Double getPrecision(ArrayList<DataPoint> data) {
        return null;
    }
}
