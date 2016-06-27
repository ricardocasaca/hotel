package com.ricardo.validacao;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ricardo on 14/06/16.
 */
public class ValidacaoEspacoTest extends ValidacaoTeste {
    @Test
    public void textoSemEspacoDeveRetornarStatusZero() throws Exception {
        validador = new ValidacaoEspaco();
        status = validador.validar("textosemespaço");
        assertTrue(status.getCodigo().equals("0"));
    }

    @Test
    public void textoComEspacoDeveRetornarStatusDiferenteDeZero() throws Exception {
        validador = new ValidacaoEspaco();
        status = validador.validar("texto com espaço");
        assertFalse(status.getCodigo().equals("0"));
    }

}