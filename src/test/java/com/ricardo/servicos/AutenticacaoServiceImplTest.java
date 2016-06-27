package com.ricardo.servicos;

import com.ricardo.interfaces.AutenticacaoService;
import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.pessoa.Usuario;
import com.ricardo.pessoa.UsuarioSimples;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ricardo on 01/06/16.
 */
public class AutenticacaoServiceImplTest {
    private UsuarioService usuarioService;
    private ConexaoHandler conexaoHandler;
    private Usuario usuario;
    private AutenticacaoService autenticacaoService;

    @Test
    public void tentaAutenticarUsuarioQueNaoExisteDeveRetornarFalse() throws Exception {
        when(this.usuarioService.existeUsuario(this.usuario)).thenReturn(false); // Garante que usuário não existe na base.
        assertFalse(this.autenticacaoService.autenticarUsuario(this.usuario)); // Retorna false porque usuário não existe.
    }

    @Test
    public void tentaAutenticarUsuarioQueExisteEValidoDeveRetornarTrue() throws Exception {
        when(this.usuarioService.existeUsuario(this.usuario)).thenReturn(true); // Garante que usuário existe na base.

        // Garante que o usuário encontrado na base de dados retorne o hash "hash".
        when(this.usuarioService.getUsuarioPorLogin(this.usuario.getLogin())).thenReturn(new UsuarioSimples(){
            @Override
            public String getHashSenha() {
                return "hash";
            }
        });
        when(this.usuario.getHashSenha()).thenReturn("hash");

        assertTrue(this.autenticacaoService.autenticarUsuario(this.usuario)); // Como o usuário existe e o hash é igual então autentica.
    }

    @Test
    public void tentaAutenticarUsuarioQueExisteEInvalidoDeveRetornarFalse() throws Exception {
        when(this.usuarioService.existeUsuario(this.usuario)).thenReturn(true); // Garante que usuário existe na base.

        // Garante que o usuário encontrado na base de dados retorne o hash "hash".
        when(this.usuarioService.getUsuarioPorLogin(this.usuario.getLogin())).thenReturn(new UsuarioSimples(){
            @Override
            public String getHashSenha() {
                return "hash";
            }
        });
        when(this.usuario.getHashSenha()).thenReturn("hashDiferente"); // Garante que o hash seja diferente.

        assertFalse(this.autenticacaoService.autenticarUsuario(this.usuario)); // Como o usuário existe e o hash não é igual então não autentica.
    }

    @Before
    public void setUp() throws Exception {
        this.usuarioService = mock(UsuarioService.class);
        this.conexaoHandler = mock(ConexaoHandler.class);
        this.usuario = mock(UsuarioSimples.class);
        this.autenticacaoService = new AutenticacaoServiceImpl(this.usuarioService);
    }
}