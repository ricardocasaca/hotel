package com.ricardo.validacao;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ricardo on 14/06/16.
 */
public class ValidacaoVazioTest extends ValidacaoTeste {
    @Test
    public void stringVaziaDeveRetornarStatusDiferenteDeZero() throws Exception {
        validador = new ValidacaoVazio();
        status = validador.validar("");
        assertFalse(status.getCodigo().equals("0"));
    }

    @Test
    public void stringNaoVaziaDeveRetornarStatusZero() throws Exception {
        validador = new ValidacaoVazio();
        status = validador.validar("string não vazia");
        assertTrue(status.getCodigo().equals("0"));
    }

}