package com.ricardo.validacao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ricardo on 14/06/16.
 */
public class ValidacaoIntNaoNegativoTest extends ValidacaoTeste {
    @Test
    public void inteiroMaiorQueZeroDeveRetornarStatusZero() throws Exception {
        validador = new ValidacaoIntNaoNegativo();
        status = validador.validar("10");
        assertTrue(status.getCodigo().equals("0"));
    }

    @Test
    public void zeroDeveRetornarStatusZero() throws Exception {
        validador = new ValidacaoIntNaoNegativo();
        status = validador.validar("0");
        assertTrue(status.getCodigo().equals("0"));
    }

    @Test
    public void inteiroNegativoDeveRetornarStatusDiferenteDeZero() throws Exception {
        validador = new ValidacaoIntNaoNegativo();
        status = validador.validar("-3");
        assertFalse(status.getCodigo().equals("0"));
    }
}