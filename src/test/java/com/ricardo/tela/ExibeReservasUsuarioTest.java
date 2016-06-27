package com.ricardo.tela;

import com.ricardo.interfaces.ReservaService;
import com.ricardo.interfaces.Tela;
import com.ricardo.pessoa.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by ricardo on 13/06/16.
 */
public class ExibeReservasUsuarioTest {
    private Usuario usuario;
    private ReservaService reservaService;
    private Tela exibeReservasUsuario;

    @Before
    public void setUp() throws Exception {
        this.usuario = mock(Usuario.class);
        this.reservaService = mock(ReservaService.class);
        this.exibeReservasUsuario = new ExibeReservasUsuario(usuario, reservaService);
    }

    @Test
    public void verificaSeGetReservasPorUsuarioEChamadoUmaVez() throws Exception {
        exibeReservasUsuario.iniciarTela();
        verify(reservaService, times(1)).getReservasPorUsuario(any(Usuario.class));
    }

    // TODO: Não consegui testar o metodo abaixo.
    @Test
    public void verificaSeGetLoginEChamadoQuandoNaoHaReservas() throws Exception {
        when(reservaService.getReservasPorUsuario(any(Usuario.class))).thenReturn(mock(List.class));
        exibeReservasUsuario.iniciarTela();
        verify(usuario, times(1)).getLogin();
    }

    @Test
    public void verificaSeGetLoginEChamadoQuandoHaReservas() throws Exception {
        exibeReservasUsuario.iniciarTela();
        verify(usuario, times(1)).getLogin();
    }
}