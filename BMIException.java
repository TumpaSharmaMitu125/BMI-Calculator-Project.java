// BMIException.java
import java.util.InputMismatchException;
import java.util.Scanner;

public class BMIException {
    static Scanner sc = new Scanner(System.in);

    // Handle numeric input safely
    public static double getValidDouble(String message) {
        double value = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print(message);
                value = sc.nextDouble();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numeric value.");
                sc.nextLine(); // clear buffer
            }
        }
        return value;
    }

    // Handle any general exception and print message
    public static void handleException(Runnable task) {
        try {
            task.run();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
