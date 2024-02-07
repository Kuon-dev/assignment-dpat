package main.java.dpat.model;

import java.util.Base64;

public class PasswordEntry {
    private String siteName;
    private String encryptedPassword;

    public PasswordEntry(String siteName, String encryptedPassword) {
        this.siteName = siteName;
        this.encryptedPassword = encryptedPassword;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }
}
