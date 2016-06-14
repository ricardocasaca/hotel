package com.ricardo.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ricardo on 01/06/16.
 */
public class ShaHashTest {
    private final String hashFixo = "RgcNS/k0+w1LBtnixG40aUTjIkRJAKQ119mpXm10NfU="; // Hash para a palavra "teste".

    /**
     * Verifica se o hash retornado é igual ao hash fixo.
     *
     * @throws Exception
     */
    @Test
    public void strToSha256() throws Exception {
        assertEquals(this.hashFixo, ShaHash.strToSha256("teste"));
    }
}