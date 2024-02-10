
package main.java.dpat.model.security.passwordstore;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class BasicPasswordStore implements PasswordStore {
    private Map<String, String> passwords = new HashMap<>();

    @Override
    public void addPasswordEntry(String siteName, String password) {
        passwords.put(siteName, password);
    }

    @Override
    public String retrievePassword(String siteName) {
        return passwords.get(siteName);
    }

    @Override
    public void deletePasswordEntry(String siteName) {
        passwords.remove(siteName);
    }

    @Override
    public List<String> getSiteNames() {
        // Return a new list constructed from the keys of the passwords map.
        return new ArrayList<>(passwords.keySet());
    }
}

