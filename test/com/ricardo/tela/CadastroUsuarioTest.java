package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.Tela;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.pessoa.Usuario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by ricardo on 13/06/16.
 */
public class CadastroUsuarioTest {
    private PromptService promptService;
    private UsuarioService usuarioService;
    private Tela cadastroUsuario;

    @Before
    public void setUp() throws Exception {
        this.promptService = mock(PromptService.class);
        this.usuarioService = mock(UsuarioService.class);

        this.cadastroUsuario = new CadastroUsuario(this.promptService, this.usuarioService);
    }

    @Test
    public void cadastraUsuarioQuandoNaoExisteAdminDeveChamarPromptQuatroVezes() throws Exception {
        when(this.usuarioService.existeAdmin()).thenReturn(false);
        when(this.promptService.getUserData(anyString())).thenReturn("qualquer coisa");
        this.cadastroUsuario.iniciarTela();
        verify(this.promptService, times(4)).getUserData(anyString());
        verify(this.usuarioService, times(1)).cadastrarUsuario(any(Usuario.class));
    }

    @Test
    public void cadastraUsuarioQuandoExisteAdminDeveChamarPromptTresVezes() throws Exception {
        when(this.usuarioService.existeAdmin()).thenReturn(true);
        when(this.promptService.getUserData(anyString())).thenReturn("qualquer coisa");
        this.cadastroUsuario.iniciarTela();
        verify(this.promptService, times(3)).getUserData(anyString());
        verify(this.usuarioService, times(1)).cadastrarUsuario(any(Usuario.class));
    }

}