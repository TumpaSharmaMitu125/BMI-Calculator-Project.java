// BMIFileHandler.java
import java.io.*;

public class BMIFileHandler {

    // Check if BMI data already saved
    public static boolean isDataSaved() {
        File file = new File("bmi_data.txt");
        return file.exists() && file.length() > 0;
    }

    // Save BMI data to file
    public static void saveBMI(double height, double weight, double bmi, String category) {
        String record = "Height: " + height + " m | Weight: " + weight + " kg | BMI: "
                        + String.format("%.2f", bmi) + " | Category: " + category;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("bmi_data.txt", true))) {
            bw.write(record);
            bw.newLine();
            System.out.println("BMI data saved successfully.");
        } catch (IOException e) {
            // Throw exception to be handled in exception handler class
            throw new RuntimeException("Error saving BMI data: " + e.getMessage());
        }
    }

    // Display saved BMI data
    public static void displaySavedData() {
        try (BufferedReader br = new BufferedReader(new FileReader("bmi_data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            // Throw exception to be handled separately
            throw new RuntimeException("Error reading saved BMI data: " + e.getMessage());
        }
    }
}
