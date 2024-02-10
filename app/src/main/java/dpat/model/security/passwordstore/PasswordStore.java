package main.java.dpat.model.security.passwordstore;

import java.util.List;

public interface PasswordStore {
    void addPasswordEntry(String siteName, String password) throws Exception;
    String retrievePassword(String siteName) throws Exception;
    void deletePasswordEntry(String siteName);
    List<String> getSiteNames();
}
