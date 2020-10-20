import java.util.*;
public class DataPoint {
    
    private double f1;
    private double f2;
    private String label;
    private String type;
    
    public DataPoint(double f1, double f2, String label, String type) {
        this.f1 = f1;
        this.f2 = f2;
        this.label = label;
        this.type = type;
    }
    
    public DataPoint() {
        this(0, 0, "", "");
    }
    
    public double getf1() {
        return this.f1;
    }
    
    public double getf2() {
        return this.f2;
    }
    
    public String getLabel() {
        return this.label;
    }

    public String getType() {
        return this.type;
    }
    
    public void setf1(double newNum) {
        f1 = newNum;
    }
    
    public void setf2(double newNum) {
        f2 = newNum;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }

    public void setType(String type) {
        this.type = type;
    }
}

