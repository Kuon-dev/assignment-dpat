package dpat;

import main.java.dpat.model.PasswordManager;
import java.util.Scanner;

public class App {
    private static PasswordManager passwordManager = new PasswordManager();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();

            if ("1".equals(option)) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                // Assuming you have a way to validate the user's login, possibly through the PasswordManager
                if (passwordManager.validateLogin(username, password)) {
                    System.out.println("Login successful.");
                    // Here you can proceed to the next part of your application
                    break; // Or continue in the app
                } else {
                    System.out.println("Login failed. Please try again.");
                }
            } else if ("2".equals(option)) {
                System.out.println("Exiting application...");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
