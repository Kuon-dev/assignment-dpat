package main.java.dpat.model.security.passwordstore.decorators;

import main.java.dpat.model.security.passwordstore.PasswordStore;
import main.java.dpat.model.security.KeyFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.List;

public class EncryptedPasswordStore extends PasswordStoreDecorator {
    private SecretKey userKey;
    private String encryptionAlgorithm;
    private KeyFactory keyFactory;

    public EncryptedPasswordStore(PasswordStore passwordStore, String encryptionAlgorithm, KeyFactory keyFactory) {
        super(passwordStore);
        this.encryptionAlgorithm = encryptionAlgorithm;
        this.keyFactory = keyFactory;
        addUserKey();
    }

    private void addUserKey() {
        int keySize = 256; // Example key size, should be set according to your security requirements
        this.userKey = keyFactory.generateKey(encryptionAlgorithm, keySize);
    }

    private String encryptPassword(String password) throws Exception {
        if (userKey == null) {
            throw new IllegalArgumentException("User key is not initialized");
        }
        Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, userKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private String decryptPassword(String encryptedPassword) throws Exception {
        if (userKey == null) {
            throw new IllegalArgumentException("User key is not initialized");
        }
        Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, userKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }

    @Override
    public void addPasswordEntry(String siteName, String password) throws Exception {
        String encryptedPassword = encryptPassword(password);
        super.addPasswordEntry(siteName, encryptedPassword);
    }

    @Override
    public String retrievePassword(String siteName) throws Exception {
        String encryptedPassword = super.retrievePassword(siteName);
        return decryptPassword(encryptedPassword);
    }

    @Override
    public void deletePasswordEntry(String siteName) {
        super.deletePasswordEntry(siteName);
    }

    @Override
    public List<String> getSiteNames() {
        return super.getSiteNames();
    }
}

