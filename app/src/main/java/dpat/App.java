package dpat;

import main.java.dpat.model.PasswordManager;
import main.java.dpat.model.LoginManager;
import java.util.Scanner;

public class App {
    private static final PasswordManager passwordManager = new PasswordManager();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.flush();
                System.out.println(ANSI_BLUE + "Welcome to the Password Manager!" + ANSI_RESET);
                System.out.println("----------------------------------");
                System.out.println("1. Login");
                System.out.println("2. Exit");
                System.out.println("----------------------------------");
                System.out.print("Choose an option: ");

                String option = scanner.nextLine();

                if ("1".equals(option)) {
                    performLogin(scanner); // Separated login logic into a method
                } else if ("2".equals(option)) {
                    System.out.println(ANSI_BLUE + "Exiting application..." + ANSI_RESET);
                    break;
                } else {
                    System.out.println(ANSI_RED + "Invalid option. Please try again." + ANSI_RESET);
                }
            }
        } finally {
            scanner.close(); // Close the scanner in a finally block to ensure it's always done
        }
    }

    private static void performLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Utilize LoginManager for authentication
        boolean isAuthenticated = LoginManager.getInstance().validateLogin(username, password);

        if (isAuthenticated) {
            System.out.println(ANSI_GREEN + "Login successful." + ANSI_RESET);
            passwordManager.view(scanner); // Pass the scanner to the view method
        } else {
            System.out.println(ANSI_RED + "Login failed. Please try again." + ANSI_RESET);
        }
    }
}

