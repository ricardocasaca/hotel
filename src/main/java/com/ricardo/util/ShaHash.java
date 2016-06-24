package com.ricardo.util;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ricardo on 16/05/16.
 * Classe utilitária utilizada para gerar um hash Sha256 a partir de uma string.
 */
public class ShaHash {
    /**
     * Gera um hash Sha256 a partir de uma string.
     *
     * @param str String da qual será gerado o hash.
     * @return String contendo o hash gerado.
     */
    public static String strToSha256(String str) {
        byte[] hashSenha;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(str.getBytes("UTF-8"));
            hashSenha = md.digest();

            return Base64.encode(hashSenha);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
