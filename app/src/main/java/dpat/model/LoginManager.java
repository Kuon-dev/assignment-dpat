package main.java.dpat.model;

import java.util.HashMap;
import java.util.Map;

public class LoginManager {

    private static LoginManager instance; // The single instance of LoginManager
    private Map<String, String> userCredentials; // Simulates a database of user credentials

    // singleton design pattern
    private LoginManager() {
        userCredentials = new HashMap<>();
        // Initialize with some user credentials for demonstration
        userCredentials.put("user1", "password1"); // username, password
        userCredentials.put("user2", "password2");
    }

    // Public method to get the instance of the class
    public static synchronized LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    // Validates user login
    public boolean validateLogin(String username, String password) {
        String storedPassword = userCredentials.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
}
