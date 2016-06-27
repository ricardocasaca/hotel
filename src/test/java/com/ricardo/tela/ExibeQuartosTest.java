package com.ricardo.tela;

import com.ricardo.interfaces.Tela;
import com.ricardo.suites.Quarto;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Created by ricardo on 13/06/16.
 */
public class ExibeQuartosTest {
    @Test
    public void verificaSePegaONumeroDoQuartoParaExibir() throws Exception {
        List<Quarto> q = mock(List.class);
        Tela exibeQuartos = new ExibeQuartos(q);
        exibeQuartos.iniciarTela();
    }

}