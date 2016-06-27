package com.ricardo.validacao;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ricardo on 14/06/16.
 */
public class ValidacaoNuloTest extends ValidacaoTeste {
    @Test
    public void stringNaoNulaDeveRetornarStatusZero() throws Exception {
        validador = new ValidacaoNulo();
        status = validador.validar("teste");
        assertTrue(status.getCodigo().equals("0"));
    }

    @Test
    public void stringNulaDeveRetornarStatusDiferenteDeZero() throws Exception {
        validador = new ValidacaoNulo();
        status = validador.validar(null);
        assertFalse(status.getCodigo().equals("0"));
    }

}