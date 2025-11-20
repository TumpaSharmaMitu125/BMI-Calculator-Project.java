import java.io.*;
import java.util.Scanner;

public class UserAuth {
    private static Scanner sc = new Scanner(System.in);

    public static boolean loginOrRegister() {
        System.out.print("\n1.Login  2.Register : ");
        int choice = sc.nextInt(); sc.nextLine(); // buffer clear

        if (choice == 1) return login();
        else if (choice == 2) { register(); return login(); }
        else { System.out.println("Invalid choice"); return loginOrRegister(); }
    }

    private static void register() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
            System.out.print("New username: "); String user = sc.nextLine();
            System.out.print("New password: "); String pass = sc.nextLine();
            bw.write(user + "," + pass); bw.newLine();
            System.out.println("Registration successful!");
        } catch (IOException e) { System.out.println("Error: " + e.getMessage()); }
    }

    private static boolean login() {
        System.out.print("Username: "); String user = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(user) && parts[1].equals(pass)) {
                    System.out.println("Login successful!");
                    return true;
                }
            }
        } catch (IOException e) { System.out.println("Error: " + e.getMessage()); }

        System.out.println("Incorrect username or password.");
        return false;
    }
}