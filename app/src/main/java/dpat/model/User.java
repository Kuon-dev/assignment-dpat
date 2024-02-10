package main.java.dpat.model;

import main.java.dpat.model.PasswordManager;
// import lombok.Getter;
// import lombok.Setter;
// import lombok.NoArgsConstructor;
//
public class User {
    private String username;
    private String masterPassword;

    public User(String username, String masterPassword) {
        this.username = username;
        this.masterPassword = masterPassword;
    }
}

