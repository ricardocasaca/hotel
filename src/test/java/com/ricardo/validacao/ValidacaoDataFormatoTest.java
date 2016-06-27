package com.ricardo.validacao;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ricardo on 14/06/16.
 */
public class ValidacaoDataFormatoTest extends ValidacaoTeste {
    @Test
    public void dataNoFormatoddmmaaaaDeveRetornarStatusZero() throws Exception {
        validador = new ValidacaoDataFormato();
        status = validador.validar("12/05/2014");
        assertTrue(status.getCodigo().equals("0"));
    }

    @Test
    public void dataNoFormatoIncorretoDeveRetornarStatusDiferenteDeZero() throws Exception {
        validador = new ValidacaoDataFormato();
        status = validador.validar("12/23/2014");
        assertFalse(status.getCodigo().equals("0"));
    }

    @Test
    public void dataForaDoLimiteDeveRetornarStatusDiferenteDeZero() throws Exception {
        validador = new ValidacaoDataFormato();
        status = validador.validar("67/23/2014");
        assertFalse(status.getCodigo().equals("0"));
    }

    @Test
    public void inputQueNaoEDataDeveRetornarStatusDiferenteDeZero() throws Exception {
        validador = new ValidacaoDataFormato();
        status = validador.validar("teste123");
        assertFalse(status.getCodigo().equals("0"));
    }

}