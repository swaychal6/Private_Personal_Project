package com.jansuraksha.utils.encryptdecrpt;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class EncryptionUtil {


    private static final int GCM_TAG_LENGTH = 16;
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String ALGO_AES = "AES";
    private static final String ALGO_RSA = "RSA";
    private static final String ENCODING = "UTF-8";
    private static final String HEADER_ENC_ALGO = "RSA/ECB/OAEPPADDING";
    private static final String SIGNATURE = "SHA256withRSA";
    private static final String SEPARATOR = ":";


    public  String encrypt(String plainText, String privateKey, String publicKey) throws NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException, NoSuchProviderException  {

        String dynamicKey = UUID.randomUUID().toString().replaceAll("-","");
        byte[] iv = getIVFromAESKey(dynamicKey.getBytes());
        byte[] encrypt = encryptRequestBodyAES256(plainText.getBytes(), dynamicKey.getBytes(),iv);
        String encryptionRequestBody = Base64.getEncoder().encodeToString(encrypt);
        String digitalSignature = signSHA256RSA(encryptionRequestBody, privateKey);
        String headerKey = getEncryptHeader(dynamicKey, publicKey);
        return Base64.getEncoder().encodeToString((headerKey + SEPARATOR + encryptionRequestBody + SEPARATOR + digitalSignature).getBytes());
    }

    private static byte[] getIVFromAESKey(byte[] encoded) {
        return Arrays.copyOfRange(encoded, 0, 16);
    }

    private byte[] encryptRequestBodyAES256(byte[] plaintext, byte[] key, byte[] IV) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGO_AES);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
        return cipher.doFinal(plaintext);
    }

    private String signSHA256RSA(String input, String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {

        byte[] b1 = DatatypeConverter.parseBase64Binary(privateKeyStr);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
        KeyFactory kf = KeyFactory.getInstance(ALGO_RSA);
        Signature privateSignature = Signature.getInstance(SIGNATURE);
        privateSignature.initSign(kf.generatePrivate(spec));
        byte[] bytes = input.getBytes(ENCODING);
        privateSignature.update(bytes);
        byte[] s = privateSignature.sign();
        return Base64.getEncoder().encodeToString(s);
    }

    private String getEncryptHeader(String keyPlainText, String publicKeyStr) {
        try {
            byte[] keyByteArr = keyPlainText.getBytes();
            PublicKey key = getPublicKey(publicKeyStr);
            Cipher cipher = Cipher.getInstance(HEADER_ENC_ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedByte = cipher.doFinal(keyByteArr);
            return Base64.getEncoder().encodeToString(encryptedByte);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    PublicKey getPublicKey(String publicKeyStr) {

        PublicKey publicKey = null;
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            BufferedReader br = new BufferedReader(new StringReader(publicKeyStr));
            String line = null;
            StringBuilder keyBuffer = new StringBuilder();

            while ((line = br.readLine()) != null) {
                if (!line.startsWith("-")) {
                    keyBuffer.append(line);
                }
            }
            Certificate certificate = certificateFactory
                    .generateCertificate(new ByteArrayInputStream(Base64.getDecoder().decode(keyBuffer.toString())));
            publicKey = certificate.getPublicKey();
        } catch (Exception var8) {
            var8.printStackTrace();
        }
        return publicKey;
    }
}
