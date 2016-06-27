package com.ricardo.validacao;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ricardo on 14/06/16.
 */
public class ValidacaoValorBinarioTest extends ValidacaoTeste {
    @Test
    public void valorZeroDeveRetornarStatusZero() throws Exception {
        validador = new ValidacaoValorBinario();
        status = validador.validar("0");
        assertTrue(status.getCodigo().equals("0"));
    }

    @Test
    public void valorUmDeveRetornarStatusZero() throws Exception {
        validador = new ValidacaoValorBinario();
        status = validador.validar("1");
        assertTrue(status.getCodigo().equals("0"));
    }

    @Test
    public void valorDiferenteDeZeroOuUmDeveRetornarStatusDiferenteDeZero() throws Exception {
        validador = new ValidacaoValorBinario();
        status = validador.validar("6");
        assertFalse(status.getCodigo().equals("1"));
    }
}