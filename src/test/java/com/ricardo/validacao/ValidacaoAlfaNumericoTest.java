package com.ricardo.validacao;

import com.ricardo.interfaces.StatusErro;
import com.ricardo.interfaces.Validador;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ricardo on 14/06/16.
 */
public class ValidacaoAlfaNumericoTest extends ValidacaoTeste{
    @Test
    public void validandoInputAlfaNumericoDeveRetornarStatusZero() throws Exception {
        validador = new ValidacaoAlfaNumerico();
        status = validador.validar("abcd1234");
        assertTrue(status.getCodigo().equals("0"));
    }

    @Test
    public void validandoInputNaoAlfaNumericoDeveRetornarStatusDiferenteDeZero() throws Exception {
        validador = new ValidacaoAlfaNumerico();
        status = validador.validar("abcd1234 ");
        assertFalse(status.getCodigo().equals("0"));
    }
}