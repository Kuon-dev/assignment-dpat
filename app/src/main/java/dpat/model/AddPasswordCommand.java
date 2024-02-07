package dpat;

@Entity
@Getter @Setter @NoArgsConstructor
public class AddPasswordCommand implements Command {
    private PasswordManager manager;
    private String siteName;
    private String password;

    public AddPasswordCommand(PasswordManager manager, String siteName, String password) {
        this.manager = manager;
        this.siteName = siteName;
        this.password = password;
    }

    @Override
    public void execute() {
        manager.addPasswordEntry(siteName, password);
    }
}

public class DeletePasswordCommand implements Command {
    private PasswordManager manager;
    private String siteName;

    public DeletePasswordCommand(PasswordManager manager, String siteName) {
        this.manager = manager;
        this.siteName = siteName;
    }

    @Override
    public void execute() {
        manager.deletePasswordEntry(siteName);
    }
}
