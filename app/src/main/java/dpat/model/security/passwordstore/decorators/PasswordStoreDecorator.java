package main.java.dpat.model.security.passwordstore.decorators;

import main.java.dpat.model.security.passwordstore.PasswordStore;

import java.util.List;

public abstract class PasswordStoreDecorator implements PasswordStore {
    protected PasswordStore passwordStore;

    public PasswordStoreDecorator(PasswordStore passwordStore) {
        this.passwordStore = passwordStore;
    }

    @Override
    public void addPasswordEntry(String siteName, String password) throws Exception {
        passwordStore.addPasswordEntry(siteName, password);
    }

    @Override
    public String retrievePassword(String siteName) throws Exception {
        return passwordStore.retrievePassword(siteName);
    }

    @Override
    public void deletePasswordEntry(String siteName) {
        passwordStore.deletePasswordEntry(siteName);
    }

    @Override
    public List<String> getSiteNames() {
        return passwordStore.getSiteNames();
    }
}

