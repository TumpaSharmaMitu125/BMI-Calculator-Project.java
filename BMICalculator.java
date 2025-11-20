//  Child Class (Inheritance + Polymorphism)
class BMICalculator extends BMIData {

    // Method Overriding (Polymorphism)
    @Override
    public double calculateBMI() {
        return getWeight() / (getHeight() * getHeight());
    }

    // Method Overloading (Polymorphism)
    public String bmiCategory(double bmi) {
        if (bmi < 18.5)
            return "Underweight";
        else if (bmi < 24.9)
            return "Normal weight";
        else if (bmi < 29.9)
            return "Overweight";
        else
            return "Obese";
    }
}
