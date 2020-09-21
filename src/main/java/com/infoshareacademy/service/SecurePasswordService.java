package com.infoshareacademy.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import javax.ejb.LocalBean;
import java.util.logging.Logger;


@LocalBean
public class SecurePasswordService {

    protected static final String PASS_ENCRYPT = "JAKIES_HASLO_DO_KODOWANIA";

    private static final Logger LOGGER = Logger.getLogger(SecurePasswordService.class.getName());

    public String encryptor(String password) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(PASS_ENCRYPT);
        String encryptedText = encryptor.encrypt(password);
        return encryptedText;
    }

    public String decryptor(String password) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(PASS_ENCRYPT);
        String decryptedText = decryptor.decrypt(password);
        return decryptedText;
    }
}
