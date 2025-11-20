// BMIMain1.java
import java.util.Scanner;

public class BMIMain1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BMICalculator calc = new BMICalculator();

        System.out.println("=== BMI Calculator ===");

        System.out.print("Enter Height (meters): ");
        double height = sc.nextDouble();

        System.out.print("Enter Weight (kg): ");
        double weight = sc.nextDouble();

        calc.setHeight(height);
        calc.setWeight(weight);

        double bmi1 = calc.calculateBMI(); // Overriding
        double bmi2 = calc.calculateBMI(height, weight); // Overloading

        System.out.printf("BMI (Overriding): %.2f\n", bmi1);
        System.out.printf("BMI (Overloading): %.2f\n", bmi2);
        System.out.println("Category: " + calc.bmiCategory(bmi1));

        sc.close();
    }
}
