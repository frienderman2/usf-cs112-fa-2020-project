import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.List;

public class Driver {
    // Helper function to split the line by commas and
    // return the values as a List of String
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public void SetUp(ArrayList<DataPoint> testList, ArrayList<DataPoint> trainList, ArrayList<DataPoint> bigList) {
        try {
            Scanner read = new Scanner(new File("titanic.csv"));
            int j = 0;
            while (read.hasNextLine()) {
                List<String> records = getRecordFromLine(read.nextLine());
                if (j > 0){
                    // Select the columns from the records and create a DataPoint object
                    String survival = records.get(1);
                    // System.out.println(records.get(5));
                    double fare;
                    try {
                        fare = Double.parseDouble(records.get(6));
                    } catch (IndexOutOfBoundsException e){
                        fare = 0;
                    }
                    double age;
                    try {
                        age = Double.parseDouble(records.get(5));
                    }catch (NumberFormatException e){
                        age = 0;
                    }

                    DataPoint example = new DataPoint(age, fare, survival, "");

                    Random rand = new Random();
                    double randNum = rand.nextDouble();
                    // 90% of the data is reserved for training
                    if (randNum < 0.9) {
                        // Set the type of DataPoint as “train” and put into its Collection
                        example.setType("train");
                        trainList.add(example);
                    } else {
                        // Set the type of DataPoint as “test” and put into its Collection
                        example.setType("test");
                        testList.add(example);
                    }
                    // Add new data point to the overarching collection in case I need it later
                    bigList.add(example);
                }
                j++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void dataSplit() {

    }

    public static void main(String[] args){
        ArrayList<DataPoint> trainSet = new ArrayList<DataPoint>();
        ArrayList<DataPoint> testSet = new ArrayList<DataPoint>();
        ArrayList<DataPoint> wholeSet = new ArrayList<DataPoint>();

        Driver thing = new Driver();
        thing.SetUp(testSet, trainSet, wholeSet);

        KNNModel modelK = new KNNModel(3);
        modelK.train(wholeSet);

        Double acc = modelK.getAccuracy(wholeSet) * 100;
        Double pre = modelK.getAccuracy(wholeSet) * 100;

        JFrame outline = new JFrame();
        Container stuffArea = outline.getContentPane();
        stuffArea.setLayout(new GridLayout(2,1));
        outline.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        stuffArea.add(new JLabel(String.valueOf(acc)));
        stuffArea.add(new JLabel(String.valueOf(pre)));
        outline.pack();
        outline.setVisible(true);


    }
}
