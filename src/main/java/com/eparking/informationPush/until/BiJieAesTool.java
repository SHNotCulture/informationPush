package com.eparking.informationPush.until;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
public class BiJieAesTool {

    private static final String SECRET_KEY_SPEC = "AES";
    private static final String ENCRYPTION_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final Charset CHARSET = Charset.forName("UTF-8");


    private static byte[] encrypt(String content, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] keyStr = password.getBytes(CHARSET);
        SecretKeySpec key = new SecretKeySpec(keyStr, SECRET_KEY_SPEC);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        byte[] byteContent = content.getBytes(CHARSET);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] result = cipher.doFinal(byteContent);
        return result;
    }

    private static byte[] decrypt(byte[] content, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] keyStr = password.getBytes(CHARSET);
        SecretKeySpec key = new SecretKeySpec(keyStr, SECRET_KEY_SPEC);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] result = cipher.doFinal(content);
        return result;
    }

    public static String encryptBase64(String content, String password) {
        try {
            byte[] encrypt = encrypt(content, password);
            return Base64.encodeBase64String(encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptBase64(String content, String password) {
        try {
            content = content.replaceAll(" ","+");
            byte[] b = decrypt(Base64.decodeBase64(content), password);
            return new String(b, CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

