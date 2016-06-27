package com.ricardo.validacao;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ricardo on 14/06/16.
 */
public class ValidacaoDataFuturaTest extends ValidacaoTeste {
    @Test
    public void dataAnteriorAoDiaDeHojeDeveRetornarStatusDiferenteDeZero() throws Exception {
        Date ontem = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        validador = new ValidacaoDataFutura();
        status = validador.validar(formater.format(ontem));
        assertFalse(status.getCodigo().equals("0"));
    }

    @Test
    public void dataFuturaAoDiaDeHojeDeveRetornarStatusZero() throws Exception {
        Date amanha = new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L);
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        validador = new ValidacaoDataFutura();
        status = validador.validar(formater.format(amanha));
        assertTrue(status.getCodigo().equals("0"));
    }
}