package main.java.dpat.model;

@Entity
@Getter @Setter @NoArgsConstructor

public class User {
    private String username;
    private String masterPassword;

    public User(String username, String masterPassword) {
        this.username = username;
        this.masterPassword = masterPassword;
    }

    public boolean login(String username, String password) {
        // In a real scenario, this method would interact with PasswordManager
        return PasswordManager.getInstance().validateLogin(username, password);
    }
}
