package com.nash.product.weixinPay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
@Component
public class WxAPIV3AesUtil {

    static final int KEY_LENGTH_BYTE = 32;
    static final int TAG_LENGTH_BIT = 128;
    private static String aesKey;


    @Value("${wechat.apiV3Key}")
    public void setName(String aesKey) {
        this.aesKey = aesKey;
    }

    public static String decryptToString(byte[] associatedData, byte[] nonce, String ciphertext)
            throws GeneralSecurityException, IOException {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            SecretKeySpec key = new SecretKeySpec(aesKey.getBytes("UTF-8"), "AES");
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce);

            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData);

            return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)), "utf-8");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public static String aesgcmDecrypt(String aad, String iv, String cipherText) throws Exception {
        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        cipher.updateAAD(aad.getBytes());
        return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
    }
}

