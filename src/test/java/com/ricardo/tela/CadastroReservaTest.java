package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.QuartoService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.interfaces.Tela;
import com.ricardo.pessoa.Usuario;
import com.ricardo.suites.Quarto;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by ricardo on 13/06/16.
 */
public class CadastroReservaTest {
    private Tela cadastroReserva;
    private Usuario usuario;
    private PromptService promptService;
    private QuartoService quartoService;
    private ReservaService reservaService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        this.usuario = mock(Usuario.class);
        this.promptService = mock(PromptService.class);
        this.quartoService = mock(QuartoService.class);
        this.reservaService = mock(ReservaService.class);

        this.cadastroReserva = new CadastroReserva(this.usuario, this.promptService, this.quartoService, this.reservaService);
    }

    @Test
    public void cadastroDeReservaParaQuartoInexistenteNaoChamaSetReservas() throws Exception {
        when(this.quartoService.existeQuarto(any(Quarto.class))).thenReturn(false); // Força não existência de quarto.
        when(this.promptService.getUserData(anyString())).thenReturn("qualquer coisa");
        this.cadastroReserva.iniciarTela();
        verify(this.reservaService, never()).getReservasPorQuarto(any(Quarto.class)); // Se o quarto não estiver cadastrado nunca chama o getReservasPorQuarto().
    }

    // TODO: Não está pegando a exceção lançada por algum motivo obscuro.
    @Test
    public void cadastroDeReservaParaQuartoExistenteChamaSetReservas() throws Exception {
        when(this.quartoService.existeQuarto(any(Quarto.class))).thenReturn(true); // Força existência de quarto.
        when(this.promptService.getUserData(anyString())).thenReturn("dsasdf");
        thrown.expect(ParseException.class);
        this.cadastroReserva.iniciarTela();
        verify(this.reservaService, times(1)).getReservasPorQuarto(any(Quarto.class)); // Se o quarto estiver cadastrado chama o getReservasPorQuarto() uma única vez.
    }

}