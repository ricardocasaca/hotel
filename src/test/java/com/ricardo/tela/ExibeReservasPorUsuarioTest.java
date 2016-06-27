package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.interfaces.Tela;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.pessoa.Usuario;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by ricardo on 13/06/16.
 */
public class ExibeReservasPorUsuarioTest {
    @Test
    public void iniciarTela() throws Exception {
        PromptService promptService = mock(PromptService.class);
        UsuarioService usuarioService = mock(UsuarioService.class);
        ReservaService reservaService = mock(ReservaService.class);
        when(promptService.getUserData(anyString())).thenReturn("qualquercoisa");
        when(usuarioService.existeUsuario(any(Usuario.class))).thenReturn(false).thenReturn(true); // Força não existência de usuário na primeira chamada e força existência na segunda.
        Tela exibeReservasPorUsuario = new ExibeReservasPorUsuario(usuarioService, reservaService, promptService);
        exibeReservasPorUsuario.iniciarTela();
        verify(promptService, times(2)).getUserData(anyString()); // Se chamar 2 vezes é porque entrou no if que verifica se o usuário existe.
        verify(reservaService, times(1)).getReservasPorUsuario(any(Usuario.class));
    }
}