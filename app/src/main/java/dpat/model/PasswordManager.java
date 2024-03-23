package main.java.dpat.model;

import main.java.dpat.model.security.passwordstore.PasswordStore;
import main.java.dpat.model.security.passwordstore.BasicPasswordStore;
import main.java.dpat.model.security.passwordstore.decorators.EncryptedPasswordStore;
import main.java.dpat.model.security.KeyFactory;
import main.java.dpat.model.security.UniqueKeyFactory;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.List;

public class PasswordManager {
    private PasswordStore passwordStore;
    private KeyFactory keyFactory;
    private String encryptionAlgorithm = "AES"; // Default encryption algorithm

    public PasswordManager() {
        this.keyFactory = new UniqueKeyFactory();
        this.initializePasswordStore();
    }

    private void initializePasswordStore() {
        PasswordStore basicStore = new BasicPasswordStore();
        this.passwordStore = new EncryptedPasswordStore(basicStore, encryptionAlgorithm, keyFactory);
    }

    public void addPasswordEntry(String siteName, String password) throws Exception {
        passwordStore.addPasswordEntry(siteName, password);
    }

    public String retrievePassword(String siteName) throws Exception {
        String password = passwordStore.retrievePassword(siteName);
        if (password == null) {
            throw new Exception("Password for the site '" + siteName + "' does not exist.");
        }
        return password;
    }

    public void deletePasswordEntry(String siteName) {
        passwordStore.deletePasswordEntry(siteName);
    }

    public boolean validatePassword(String password) {
        // Example criteria: at least 8 characters, contains a digit, a lowercase, an uppercase, and a special character
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return Pattern.compile(regex).matcher(password).matches();
    }

    public void view(Scanner scanner) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_BLUE = "\u001B[34m";

        int option = 0;
        while (option != 7) {
            System.out.println("----------------------------------");
            System.out.println("\nPassword Manager Menu:");
            System.out.println("1. Add Password Entry");
            System.out.println("2. Retrieve Password");
            System.out.println("3. Delete Password Entry");
            System.out.println("4. Validate Password");
            System.out.println("5. List All Sites");
            System.out.println("6. Exit");
            System.out.println("----------------------------------");
            System.out.print("Select an option (1-7): ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Please enter a valid number." + ANSI_RESET);
                continue;
            }
            try {
                switch (option) {
                    case 1:
                        System.out.flush();
                        System.out.println("Enter site name:");
                        String site = scanner.nextLine();
                        System.out.println("Enter password:");
                        String newPassword = scanner.nextLine();
                        if (validatePassword(newPassword)) {
                            addPasswordEntry(site, newPassword);
                            System.out.println(ANSI_GREEN + "Password entry added." + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_RED + "Password does not meet the criteria." + ANSI_RESET);
                        }
                        break;
                    case 2:
                        System.out.println(ANSI_BLUE + "Enter site name to retrieve password:" + ANSI_RESET);
                        try {
                            String siteToRetrieve = scanner.nextLine();
                            String retrievedPassword = retrievePassword(siteToRetrieve);
                            System.out.println(ANSI_GREEN + "Success: Password for " + siteToRetrieve + ": " + retrievedPassword + ANSI_RESET);
                        } catch (Exception e) {
                            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
                        }                        
                        break;
                    case 3:
                        System.out.println(ANSI_BLUE + "Enter site name to delete password entry:" + ANSI_RESET);
                        String siteToDelete = scanner.nextLine();
                        deletePasswordEntry(siteToDelete);
                        System.out.println(ANSI_BLUE + "Password entry deleted." + ANSI_RESET);
                        break;
                    case 4:
                        System.out.println("Enter password to validate:");
                        String passwordToValidate = scanner.nextLine();
                        if (validatePassword(passwordToValidate)) {
                            System.out.println(ANSI_BLUE + "Password is strong." + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_RED + "Password is weak." + ANSI_RESET);
                        }
                        break;
                    case 5:
                        listSiteNames();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private void listSiteNames() {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLUE = "\u001B[34m";

        System.out.println("Stored Sites:");
        List<String> siteNames = passwordStore.getSiteNames();
        if (siteNames.isEmpty()) {
            System.out.println("No sites stored.");
        } else {
            for (String site : siteNames) {
                System.out.println(ANSI_BLUE + site + ANSI_RESET);
            }
        }
    }
}
