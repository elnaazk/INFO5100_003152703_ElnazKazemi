import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.*;
import java.util.Base64;

public class Exercise6 {

    // Utility to print nicely
    private static void print(String title, String value) {
        System.out.println("\n=== " + title + " ===");
        System.out.println(value);
    }

    public static void main(String[] args) throws Exception {

        // 1. SYMMETRIC AES-256 GCM
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey aesKey = keyGen.generateKey();

        String message = "Hello Bob! This is Alice (AES).";

        Cipher aesEncrypt = Cipher.getInstance("AES/GCM/NoPadding");
        aesEncrypt.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] iv = aesEncrypt.getIV();
        byte[] cipherText = aesEncrypt.doFinal(message.getBytes());

        Cipher aesDecrypt = Cipher.getInstance("AES/GCM/NoPadding");
        aesDecrypt.init(Cipher.DECRYPT_MODE, aesKey, new GCMParameterSpec(128, iv));
        String decryptedAES = new String(aesDecrypt.doFinal(cipherText));

        print("AES Ciphertext", Base64.getEncoder().encodeToString(cipherText));
        print("AES Decrypted Text", decryptedAES);


        // 2. ASYMMETRIC RSA-2048
        KeyPairGenerator rsaGen = KeyPairGenerator.getInstance("RSA");
        rsaGen.initialize(2048);
        KeyPair bobKeyPair = rsaGen.generateKeyPair();
        PublicKey bobPublic = bobKeyPair.getPublic();
        PrivateKey bobPrivate = bobKeyPair.getPrivate();

        String rsaMessage = "Hello Bob! This is Alice (RSA).";

        Cipher rsaEncrypt = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaEncrypt.init(Cipher.ENCRYPT_MODE, bobPublic);
        byte[] rsaCipher = rsaEncrypt.doFinal(rsaMessage.getBytes());

        Cipher rsaDecrypt = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaDecrypt.init(Cipher.DECRYPT_MODE, bobPrivate);
        String rsaDecrypted = new String(rsaDecrypt.doFinal(rsaCipher));

        print("RSA Ciphertext", Base64.getEncoder().encodeToString(rsaCipher));
        print("RSA Decrypted Text", rsaDecrypted);

        // 3. DIGITAL SIGNATURE
        KeyPair aliceKeyPair = rsaGen.generateKeyPair();  // separate keys for Alice
        PrivateKey alicePrivate = aliceKeyPair.getPrivate();
        PublicKey alicePublic = aliceKeyPair.getPublic();

        String signMessage = "Bob, this is Alice. Please verify my signature.";

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(alicePrivate);
        signature.update(signMessage.getBytes());
        byte[] digitalSig = signature.sign();

        Signature verifySig = Signature.getInstance("SHA256withRSA");
        verifySig.initVerify(alicePublic);
        verifySig.update(signMessage.getBytes());

        boolean isValid = verifySig.verify(digitalSig);

        print("Digital Signature", Base64.getEncoder().encodeToString(digitalSig));
        print("Signature Valid?", String.valueOf(isValid));
    }
}
