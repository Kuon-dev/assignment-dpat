package dpat;

import main.java.dpat.model.PasswordManager;
import main.java.dpat.model.User;
import java.util.Scanner;

public class App {
    private static final User defaultUser = new User("defaultUser", "masterPass");
    private static final PasswordManager passwordManager = new PasswordManager();

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
                System.out.print("Enter master password: ");
                String password = scanner.nextLine();

                if ("defaultUser".equals(username) && defaultUser.validateMasterPassword(password)) {
                    System.out.println("Login successful.");
                    passwordManager.view(); // Display PasswordManager options instead of exiting
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
