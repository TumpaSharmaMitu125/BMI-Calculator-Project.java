// BMIData.java
public abstract class BMIData {
    private double height;
    private double weight;

    // Setter methods
    public void setHeight(double height) { this.height = height; }
    public void setWeight(double weight) { this.weight = weight; }

    // Getter methods
    public double getHeight() { return height; }
    public double getWeight() { return weight; }

    // Abstract method to calculate BMI
    public abstract double calculateBMI();
}
