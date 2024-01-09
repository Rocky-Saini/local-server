package com.digital.signage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class AESFileEncryptor {

    private static final Logger logger = LoggerFactory.getLogger(AESFileEncryptor.class);

    private final static String saltString = "bef825-1d6b-422f";

    static byte[] buf = null;

    private AESFileEncryptor() {
        // Throw an exception if this ever is called
        throw new AssertionError("Instantiating utility class not allowed.");
    }

    private static Cipher makecipher(String strkey) throws GeneralSecurityException, UnsupportedEncodingException {

        //Set up the cipher:
        Cipher ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        byte[] keyBytes = new byte[16];
        byte[] iv = new byte[16];

        keyBytes = strkey.getBytes(StandardCharsets.UTF_8);
        Key key = new SecretKeySpec(keyBytes, "AES");

        iv = saltString.getBytes();
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        try {
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        } catch (Exception e) {
            logger.error("", e);
        }
        return ecipher;
    }

    public static void encrypt(InputStream in, OutputStream out, String pwd) throws GeneralSecurityException {
        try {
            byte[] buf = new byte[1024];
            Cipher ecipher = makecipher(pwd);

            out = new CipherOutputStream(out, ecipher);

            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (IOException e) {
            logger.error("", e);
        }
    }
}