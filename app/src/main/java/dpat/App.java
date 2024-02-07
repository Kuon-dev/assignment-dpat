package dpat;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the CLI Password Manager");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(username, password);
        if (user.login(username, password)) {
            System.out.println("Login successful.");
            // Proceed with application logic
        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }
    }
}

