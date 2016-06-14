package com.ricardo.validacao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ricardo on 14/06/16.
 */
public class ValidacaoTamanhoInputTest extends ValidacaoTeste {
    private int tamanhoInput = 5;

    @Test
    public void inputDeTamanhoInferiorAoLimiteRetornaStatusZero() throws Exception {
        validador = new ValidacaoTamanhoInput(this.tamanhoInput);
        status = validador.validar("123");
        assertTrue(status.getCodigo().equals("0"));
    }

    @Test
    public void inputDeTamanhoSuperiorAoLimiteRetornaStatusDiferenteDeZero() throws Exception {
        validador = new ValidacaoTamanhoInput(this.tamanhoInput);
        status = validador.validar("123456");
        assertFalse(status.getCodigo().equals("0"));
    }

    @Test
    public void inputDeTamanhoIgualAoLimiteRetornaStatusZero() throws Exception {
        validador = new ValidacaoTamanhoInput(this.tamanhoInput);
        status = validador.validar("12345");
        assertTrue(status.getCodigo().equals("0"));
    }

}