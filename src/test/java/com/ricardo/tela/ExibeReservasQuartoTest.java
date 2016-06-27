package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.QuartoService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.interfaces.Tela;
import com.ricardo.suites.Quarto;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by ricardo on 13/06/16.
 */
public class ExibeReservasQuartoTest {
    @Test
    public void iniciarTela() throws Exception {
        ReservaService reservaService = mock(ReservaService.class);
        QuartoService quartoService = mock(QuartoService.class);
        PromptService promptService = mock(PromptService.class);
        Tela exibeReservasQuarto = new ExibeReservasQuarto(reservaService, quartoService, promptService);
        when(quartoService.existeQuarto(any(Quarto.class))).thenReturn(true); // Força existência do quarto.
        exibeReservasQuarto.iniciarTela();
        verify(quartoService, times(1)).existeQuarto(any(Quarto.class));
        verify(reservaService, times(1)).getReservasPorQuarto(any(Quarto.class));
    }
}