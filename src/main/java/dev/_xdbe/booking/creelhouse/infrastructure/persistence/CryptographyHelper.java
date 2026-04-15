package dev._xdbe.booking.creelhouse.infrastructure.persistence;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;
import java.nio.ByteBuffer;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;

@Component
public class CryptographyHelper {

    // private static final String AES = "AES/GCM/NoPadding";
    private static final String SECRET = "secret-key-12345";
    private static final int CRYPTO_IV_LENGTH = 16;
    private static final int CRYPTO_AUTH_TAG_LENGTH = 128;

    public static String encryptData(String plainData) {
        try {
            SecretKey secretKey = new SecretKeySpec(SECRET.getBytes(), "AES");

            //build the initialization vector
            byte[] iv = new byte[CRYPTO_IV_LENGTH];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(iv);

            GCMParameterSpec parameterSpec = new GCMParameterSpec(CRYPTO_AUTH_TAG_LENGTH, iv); //128-bit authentication tag length

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

            byte[] cipherText = cipher.doFinal(plainData.getBytes());

            ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + cipherText.length);
            byteBuffer.put(iv);
            byteBuffer.put(cipherText);

            //the first 12 bytes are the IV where others are the cipher message + authentication tag
            byte[] cipherMessage = byteBuffer.array();
            return Base64.getEncoder().encodeToString(cipherMessage);
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            //throw new IllegalStateException(e);
            //System.out.println("Error decrypting credit card number");
            throw new RuntimeException("Error encrypting credit card number", e);
        }
    }

    public static String decryptData(String encryptedDataInBase64) {
        try {
            SecretKey secretKey = new SecretKeySpec(SECRET.getBytes(), "AES");
        
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        
            byte[] encryptedDataBytes = Base64.getDecoder().decode(encryptedDataInBase64.getBytes());
        
            //remember we stored the IV as the first 12 bytes while encrypting?
            byte[] iv = Arrays.copyOfRange(encryptedDataBytes, 0, CRYPTO_IV_LENGTH);
        
            GCMParameterSpec parameterSpec = new GCMParameterSpec(CRYPTO_AUTH_TAG_LENGTH, iv); //128-bit authentication tag length
            cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);
        
            //use everything from 12 bytes on as ciphertext
            byte [] cipherBytes = Arrays.copyOfRange(encryptedDataBytes, CRYPTO_IV_LENGTH, encryptedDataBytes.length);
        
            byte[] plainText = cipher.doFinal(cipherBytes);
        
            return new String(plainText);
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            //throw new IllegalStateException(e);
            //System.out.println("Error decrypting credit card number");
            throw new RuntimeException("Error decrypting credit card number", e);
        }
    
    }
}
