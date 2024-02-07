package main.java.dpat.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.Base64;

public class PasswordManager {
    private List<PasswordEntry> passwords;
    private EncryptionStrategy encryptionStrategy;

    public PasswordManager() {
        this.passwords = new ArrayList<>();
        this.encryptionStrategy = new AES(); // Default to AES, can be changed dynamically
    }

    public void addPasswordEntry(String siteName, String password) {
        String encryptedPassword = encryptPassword(password);
        passwords.add(new PasswordEntry(siteName, encryptedPassword));
    }

    public String retrievePassword(String siteName) {
        for (PasswordEntry entry : passwords) {
            if (entry.getSiteName().equals(siteName)) {
                return decryptPassword(entry.getEncryptedPassword());
            }
        }
        return null;
    }

    public void deletePasswordEntry(String siteName) {
        passwords.removeIf(entry -> entry.getSiteName().equals(siteName));
    }

    public boolean validatePassword(String password) {
        return password != null && password.length() >= 8;
    }

    public void configureEncryptionSettings(EncryptionStrategy strategy) {
        this.encryptionStrategy = strategy;
    }

    private String encryptPassword(String password) {
        // Use the configured encryption strategy
        return encryptionStrategy.encrypt(password);
    }

    private String decryptPassword(String encryptedPassword) {
        // Use the configured encryption strategy
        return encryptionStrategy.decrypt(encryptedPassword);
    }

    public void performBatchOperations(List<Command> commands) {
        for (Command command : commands) {
            command.execute();
        }
    }
    // Other methods omitted for brevity
}

