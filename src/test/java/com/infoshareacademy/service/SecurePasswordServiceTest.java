package com.infoshareacademy.service;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurePasswordServiceTest {

    protected static final String PASS_ENCRYPT = "JAKIES_HASLO_DO_KODOWANIA";

    @Test
    void encryptor() {
        SecurePasswordService securePasswordService = new SecurePasswordService();
        String password = "password";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(PASS_ENCRYPT);
        String encryptedText = encryptor.encrypt(password);
        Assertions.assertThat(securePasswordService.decryptor(encryptedText).equals(password));

    }

    @Test
    void decryptor() {
        SecurePasswordService securePasswordService = new SecurePasswordService();
        String password = "password";
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(PASS_ENCRYPT);
        String decryptedText = decryptor.encrypt(password);
        Assertions.assertThat(securePasswordService.encryptor(decryptedText).equals(password));
    }
}