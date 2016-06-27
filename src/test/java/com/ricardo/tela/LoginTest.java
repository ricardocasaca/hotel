package com.ricardo.tela;

import com.ricardo.interfaces.AutenticacaoService;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.Tela;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.pessoa.Usuario;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by ricardo on 14/06/16.
 */
public class LoginTest {
    private PromptService promptService;
    private AutenticacaoService autenticacaoService;
    private UsuarioService usuarioService;
    private GerenciamentoDeTela gerenciamentoDeTela;
    private Tela login;

    @Before
    public void setUp() throws Exception {
        this.promptService = mock(PromptService.class);
        this.autenticacaoService = mock(AutenticacaoService.class);
        this.usuarioService = mock(UsuarioService.class);
        this.gerenciamentoDeTela = mock(GerenciamentoDeTela.class);

        this.login = new Login(this.promptService, this.autenticacaoService, this.usuarioService, this.gerenciamentoDeTela);
    }

    /**
     * Deve verificar se os métodos getUsuarioPorLogin(), setTela() e exibirTela() são chamados apenas uma vez.
     * @throws Exception
     */
    @Test
    public void testaLoginDeUsuarioAdmin() throws Exception {
        this.testeDeLogin(true);
    }

    /**
     * Deve verificar se os métodos getUsuarioPorLogin(), setTela() e exibirTela() são chamados apenas uma vez.
     * @throws Exception
     */
    @Test
    public void testaLoginDeUsuarioComum() throws Exception {
        this.testeDeLogin(false);
    }

    private void testeDeLogin(boolean isAdmin){
        Usuario u = mock(Usuario.class);
        when(u.isAdmin()).thenReturn(isAdmin);
        when(this.usuarioService.getUsuarioPorLogin(anyString())).thenReturn(u);
        when(this.promptService.getUserData(anyString())).thenReturn("qualquercoisa");
        when(this.autenticacaoService.autenticarUsuario(any(Usuario.class))).thenReturn(true); // Autentica usuário.
        this.login.iniciarTela();
        verify(this.usuarioService, times(1)).getUsuarioPorLogin(anyString());
        verify(this.gerenciamentoDeTela, times(1)).setTela(any(Tela.class));
        verify(this.gerenciamentoDeTela, times(1)).exibirTela();
    }
}