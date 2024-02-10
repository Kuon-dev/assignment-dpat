package main.java.dpat.model.security;

import javax.crypto.SecretKey;

public interface KeyFactory {
    SecretKey generateKey(String algorithm, int keySize);
}
