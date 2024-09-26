package com.jansuraksha.utils.encryptdecrpt;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class DecryptionUtil {


    private static final int GCM_TAG_LENGTH = 16;
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String ALGO_AES = "AES";
    private static final String ALGO_RSA = "RSA";
    private static final String ENCODING = "UTF-8";
    private static final String HEADER_ENC_ALGO = "RSA/ECB/OAEPPADDING";
    private static final String SIGNATURE = "SHA256withRSA";
    private static final String SEPARATOR = ":";


    public String decrypt(String encText, String privateKey, String publicKey)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, SignatureException, InvalidAlgorithmParameterException, NoSuchProviderException, InvalidKeySpecException, CertificateException, IOException {

        String split[] = new String(Base64.getDecoder().decode(encText)).split(SEPARATOR);
        String headerKey = split[0];
        String encryptionRequestBody = split[1];
        String digitalSignature = split[2];
        String decryptedHeader = decryptHeader(headerKey, privateKey);
        String decrypt = decSignSHA256RSA(encryptionRequestBody, digitalSignature, publicKey);
        byte[] aesKey = decryptedHeader.getBytes();
        byte[] iv = getIVFromAESKey(aesKey);
        return decryptRequestBody(Base64.getDecoder().decode(decrypt), decryptedHeader.getBytes(), iv);
    }

    private String decryptRequestBody(byte[] cipherText, byte[] key, byte[] IV)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException {

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGO_AES);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
        byte[] decryptedText = cipher.doFinal(cipherText);
        return new String(decryptedText);
    }


    private String decryptHeader(String data, String privatekey) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {

        byte[] encryptedData = Base64.getDecoder().decode(data);
        PrivateKey privateKey = getPrivateKey(privatekey);
        Cipher cipher = Cipher.getInstance(HEADER_ENC_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encryptedByte = cipher.doFinal(encryptedData);
        return new String(encryptedByte);
    }

    private static byte[] getIVFromAESKey(byte[] encoded) {
        return Arrays.copyOfRange(encoded, 0, 16);
    }

    private PrivateKey getPrivateKey(String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {

        PrivateKey privateKey = null;
        KeyFactory keyFactory = null;
        byte[] encoded = DatatypeConverter.parseBase64Binary(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        keyFactory = KeyFactory.getInstance(ALGO_RSA);
        privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    private String decSignSHA256RSA(String encryptionRequestBody, String digitalSignature, String strPk)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, CertificateException, IOException {

        String k = strPk.replaceAll("\n", "").replace("\r", "");
        Signature privateSignature = Signature.getInstance(SIGNATURE);
        privateSignature.initVerify(getPublicKey(k));
        byte[] bytes = encryptionRequestBody.getBytes(ENCODING);
        privateSignature.update(bytes);
        privateSignature.verify(Base64.getDecoder().decode(digitalSignature));
        return encryptionRequestBody;
    }

    private PublicKey getPublicKey(String publicKeyStr) {

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
