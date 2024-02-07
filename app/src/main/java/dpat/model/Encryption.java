package main.java.dpat.model;

public class AES implements EncryptionStrategy {
    @Override
    public String encrypt(String data) {
        // Placeholder for AES encryption logic
        return "AES(" + data + ")";
    }

    @Override
    public String decrypt(String data) {
        // Placeholder for AES decryption logic
        return "decryptedAES";
    }
}

public class RSA implements EncryptionStrategy {
    @Override
    public String encrypt(String data) {
        // Placeholder for RSA encryption logic
        return "RSA(" + data + ")";
    }

    @Override
    public String decrypt(String data) {
        // Placeholder for RSA decryption logic
        return "decryptedRSA";
    }
}
