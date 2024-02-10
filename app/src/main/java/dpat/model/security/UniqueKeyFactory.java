package main.java.dpat.model.security;

import main.java.dpat.model.security.*;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class UniqueKeyFactory implements KeyFactory {

    @Override
    public SecretKey generateKey(String algorithm, int keySize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            keyGenerator.init(keySize); // Initialize with the specified key size
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Encryption algorithm not supported", e);
        }
    }
}
