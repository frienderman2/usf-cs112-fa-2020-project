import java.util.*;
import java.math.*;

public class KNNModel extends Model{
    private ArrayList<DataPoint> trainSet = new ArrayList<>();
    private int k;
    private int Tsurv = 0;
    private int Tdie = 0;

    public KNNModel(int K){
        if (K % 2 == 0){
            K = K + 1;
        }
        this.k = K;

    }

    private double getDistance(DataPoint p1, DataPoint p2){
        double finDist;
        finDist = Math.sqrt(((p2.getf1() - p1.getf1()) * (p2.getf1() - p1.getf1())) + ((p2.getf2() - p1.getf2()) * (p2.getf2() - p1.getf2())));

        return finDist;
    }

    @Override
    void train(ArrayList<DataPoint> data) {
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).getType().equals("train")){
                DataPoint trainee = data.get(i);
                trainSet.add(trainee);
                if(data.get(i).getLabel().equals("1")){
                    Tsurv = Tsurv + 1;
                }
                else {
                    Tdie = Tdie + 1;
                }
            }
        }
    }

    @Override
    String test(ArrayList<DataPoint> data) {
        String finalAns;
        Double[][] twoDee = new Double[trainSet.size()][2];
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).getType().equals("test")){
                //twoDee = new Double[trainSet.size()][2];
                for (int j = 0; j < trainSet.size(); j++){
                    twoDee[j][0] = getDistance(data.get(i), trainSet.get(j));
                    Double placeHolder = Double.parseDouble(trainSet.get(j).getLabel()); 
                    twoDee[j][1] = placeHolder;
                }
            }
        }

        Arrays.sort(twoDee, new java.util.Comparator<Double[]>() { public int compare(Double[] a, Double [] b) { return a[0].compareTo(b[0]); }});
        
        ArrayList<String> kList = new ArrayList<>();
        
        for (int i = 0; i < this.k; i++){
            String thisLbl = Double.toString(twoDee[k][1]);
            kList.add(thisLbl);
        }

        int lbl0Counter = 0;
        int lbl1Counter = 0;
        for(int i = 0; i < kList.size(); i++){
            if (kList.get(i).equals("0")){
                lbl0Counter++;
            }
            else {
                lbl1Counter++;
            }
        }

        if (lbl1Counter > lbl0Counter){
            finalAns = "1";
        }
        else {
            finalAns = "0";
        }

        return finalAns;
    }

    @Override
    Double getAccuracy(ArrayList<DataPoint> data) {
        Double truePositive = 0.0;
        Double falsePositive = 0.0;
        Double falseNegative = 0.0;
        Double trueNegative = 0.0;
        for (DataPoint d: data) {
            String testLbl = test(data);
            String realLbl = d.getLabel();
            if (testLbl.equals(realLbl) && realLbl.equals("1")) {
                truePositive++;
            }
            else if(testLbl.equals("1") && realLbl.equals("0")){
                falsePositive++;
            }
            else if(testLbl.equals("0") && realLbl.equals("1")){
                falseNegative++;
            }
            else if(testLbl.equals(realLbl) && realLbl.equals("0")){
                trueNegative++;
            }
        }

        Double finalAns = (truePositive + trueNegative)/(truePositive + trueNegative + falsePositive + falseNegative);

        return finalAns;
    }

    @Override
    Double getPrecision(ArrayList<DataPoint> data) {
        Double truePositive = 0.0;
        Double falsePositive = 0.0;
        Double falseNegative = 0.0;
        Double trueNegative = 0.0;
        for (DataPoint d: data) {
            String testLbl = test(data);
            String realLbl = d.getLabel();
            if (testLbl.equals(realLbl) && realLbl.equals("1")) {
                truePositive++;
            }
            else if(testLbl.equals("1") && realLbl.equals("0")){
                falsePositive++;
            }
            else if(testLbl.equals("0") && realLbl.equals("1")){
                falseNegative++;
            }
            else if(testLbl.equals(realLbl) && realLbl.equals("0")){
                trueNegative++;
            }
        }

        Double finalAns = truePositive / (truePositive + falseNegative);

        return finalAns;
    }
}
