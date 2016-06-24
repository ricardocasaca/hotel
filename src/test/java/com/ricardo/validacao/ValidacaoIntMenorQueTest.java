package com.ricardo.validacao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ricardo on 14/06/16.
 */
public class ValidacaoIntMenorQueTest extends ValidacaoTeste {
    private int limite = 5;

    @Test
    public void valorAbaixoOuIgualLimiteDeveRetornarStatusZero() throws Exception {
        validador = new ValidacaoIntMenorQue(this.limite);
        status = validador.validar(String.valueOf(this.limite - 1));
        assertTrue(status.getCodigo().equals("0"));
        status = validador.validar(String.valueOf(this.limite));
        assertTrue(status.getCodigo().equals("0"));
    }

    @Test
    public void valorAcimaDoLimiteDeveRetornarStatusDiferenteDeZero() throws Exception {
        validador = new ValidacaoIntMenorQue(this.limite);
        status = validador.validar(String.valueOf(this.limite + 1));
        assertFalse(status.getCodigo().equals("0"));
    }

}