package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.interfaces.Tela;
import org.junit.Test;

import java.util.Date;

import static org.mockito.Mockito.*;

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
        verify(reservaService, times(1)).getReservasPorData(any(Date.class));
    }
}