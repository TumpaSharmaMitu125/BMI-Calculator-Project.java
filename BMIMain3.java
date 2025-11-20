import java.io.*;
import java.util.*;

// Abstract class for BMI
abstract class BMIData {
    private double height;
    private double weight;

    public void setHeight(double height) { this.height = height; }
    public void setWeight(double weight) { this.weight = weight; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }

    public abstract double calculateBMI();
}

// BMI Calculator Class
class BMICalculator extends BMIData {

    @Override
    public double calculateBMI() { // Overriding
        return getWeight() / (getHeight() * getHeight());
    }

    public double calculateBMI(double height, double weight) { // Overloading
        return weight / (height * height);
    }

    public String bmiCategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 24.9) return "Normal weight";
        else if (bmi < 29.9) return "Overweight";
        else return "Obese";
    }
}

// Main Class
public class BMIMain3 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to BMI Calculator System ===");

        if (!loginOrRegister()) {
            System.out.println("Login failed. Exiting program...");
            return;
        }

        // Check if BMI data already saved
        if (isDataSaved()) {
            System.out.println("\nBMI data already saved previously. Showing saved data:");
            displaySavedData();
        } else {
            // First time run → take input
            BMICalculator calc = new BMICalculator();

            try {
                System.out.print("Enter Height (meters): ");
                double height = sc.nextDouble();

                System.out.print("Enter Weight (kg): ");
                double weight = sc.nextDouble();

                calc.setHeight(height);
                calc.setWeight(weight);

                double bmi1 = calc.calculateBMI(); // Overriding
                double bmi2 = calc.calculateBMI(height, weight); // Overloading

                System.out.printf("\nBMI (Overriding): %.2f\n", bmi1);
                System.out.printf("BMI (Overloading): %.2f\n", bmi2);

                String category = calc.bmiCategory(bmi1);
                System.out.println("Category: " + category);

                saveBMI(height, weight, bmi1, category); // Save to file

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numeric values.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- Login / Registration ----------------
    public static boolean loginOrRegister() {
        System.out.println("\n1. Login");
        System.out.println("2. Register");
        System.out.print("Choose option: ");
        int choice = sc.nextInt();
        sc.nextLine(); // clear buffer

        if (choice == 1) return login();
        else if (choice == 2) { register(); return login(); }
        else { System.out.println("Invalid choice"); return false; }
    }

    public static void register() {
        try (FileWriter fw = new FileWriter("users.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            System.out.print("Enter new username: ");
            String username = sc.nextLine();
            System.out.print("Enter new password: ");
            String password = sc.nextLine();

            bw.write(username + "," + password);
            bw.newLine();

            System.out.println("Registration successful!");
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public static boolean login() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    System.out.println("Login successful!");
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No users found. Please register first.");
        } catch (IOException e) {
            System.out.println("Error reading user file: " + e.getMessage());
        }

        System.out.println("Incorrect username or password.");
        return false;
    }

    // ---------------- Check if BMI Data Already Saved ----------------
    public static boolean isDataSaved() {
        File file = new File("bmi_data.txt");
        return file.exists() && file.length() > 0; // if file exists and not empty
    }

    // ---------------- Display Saved BMI Data ----------------
    public static void displaySavedData() {
        try (BufferedReader br = new BufferedReader(new FileReader("bmi_data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading saved BMI data: " + e.getMessage());
        }
    }

    // ---------------- Save BMI Data to File ----------------
    public static void saveBMI(double height, double weight, double bmi, String category) {
        String record = "Height: " + height + " m | Weight: " + weight + " kg | BMI: "
                        + String.format("%.2f", bmi) + " | Category: " + category;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("bmi_data.txt", true))) {
            bw.write(record);
            bw.newLine();
            System.out.println("BMI data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving BMI data: " + e.getMessage());
        }
    }
}