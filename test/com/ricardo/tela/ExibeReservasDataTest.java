package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.interfaces.Tela;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by ricardo on 13/06/16.
 */
public class ExibeReservasDataTest {
    @Test
    public void deveChamarGetReservasPorData() throws Exception {
        PromptService promptService = mock(PromptService.class);
        ReservaService reservaService = mock(ReservaService.class);
        Tela exibeReservasData = new ExibeReservasData(promptService, reservaService);
        exibeReservasData.iniciarTela();
        verify(reservaService, times(1)).getReservasPorData(anyString());
    }
}