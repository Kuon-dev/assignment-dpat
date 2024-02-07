package main.java.dpat.model;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.HashMap;
import java.util.Base64;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PasswordManager {
    // private List<PasswordEntry> passwords;
    private String encryptionAlgorithm;
    private SecretKey secretKey;
    private Map<String, String> passwords = new HashMap<>();

    public PasswordManager() {
        this.configureEncryptionSettings("AES");
        this.encryptionAlgorithm = "AES";
        this.secretKey = generateKey();
    }

    private SecretKey generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionAlgorithm);
            keyGenerator.init(256); // Use 256-bit AES for encryption
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate encryption key", e);
        }
    }

    private String encryptPassword(String password) throws Exception {
        Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private String decryptPassword(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }

    public void addPasswordEntry(String siteName, String password) throws Exception {
        String encryptedPassword = encryptPassword(password);
        passwords.put(siteName, encryptedPassword);
    }

    public String retrievePassword(String siteName) throws Exception {
        String encryptedPassword = passwords.get(siteName);
        if (encryptedPassword == null) {
            return null; // Or throw an exception if preferred
        }
        return decryptPassword(encryptedPassword);
    }

    public void deletePasswordEntry(String siteName) {
        passwords.remove(siteName);
    }

    public boolean validatePassword(String password) {
        // Example criteria: at least 8 characters, contains a digit, a lowercase, an uppercase, and a special character
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return Pattern.compile(passwordPattern).matcher(password).matches();
    }

    public void configureEncryptionSettings(String encryptionAlgorithm) {
        if ("AES".equals(encryptionAlgorithm) || "DES".equals(encryptionAlgorithm)) {
            this.encryptionAlgorithm = encryptionAlgorithm;
            this.secretKey = generateKey(); // Re-generate key based on new algorithm
        } else {
            throw new IllegalArgumentException("Unsupported encryption algorithm: " + encryptionAlgorithm);
        }
    }

    public boolean validateLogin(String username, String password) {
        try {
            String encryptedPassword = passwords.get(username);
            if (encryptedPassword == null) {
                return false; // Username not found
            }
            String decryptedPassword = decryptPassword(encryptedPassword);
            return decryptedPassword.equals(password);
        } catch (Exception e) {
            System.out.println("Error during login validation: " + e.getMessage());
            return false;
        }
    }

    public void view() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 7) {
            System.out.println("\nPassword Manager Menu:");
            System.out.println("1. Add Password Entry");
            System.out.println("2. Retrieve Password");
            System.out.println("3. Delete Password Entry");
            System.out.println("4. Validate Password");
            System.out.println("5. Configure Encryption Settings");
            System.out.println("6. List All Passwords"); // Changed from 'Exit' to 'List All Passwords'
            System.out.println("7. Return to Main Menu"); // New option to exit this view
            System.out.print("Select an option (1-7): ");

            try {
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    // Other case implementations remain unchanged...

                    case 6: // Updated to list all passwords in a table format
                        System.out.println("Stored Passwords:");
                        System.out.printf("%-20s %s%n", "Site Name", "Password");
                        passwords.forEach((site, encryptedPassword) -> {
                            try {
                                String decryptedPassword = decryptPassword(encryptedPassword);
                                System.out.printf("%-20s %s%n", site, decryptedPassword);
                            } catch (Exception e) {
                                System.out.println("Error decrypting password for: " + site);
                            }
                        });
                        break;
                    case 7: // New case to exit the view
                        System.out.println("Returning to main menu...");
                        return; // Exit the view method
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                option = 0; // Ensure the menu is displayed again
            }
        }
    }
}

