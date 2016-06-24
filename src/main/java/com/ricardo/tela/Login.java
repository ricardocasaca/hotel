package com.ricardo.tela;

import com.ricardo.conexao.ConexaoHandlerHolder;
import com.ricardo.interfaces.*;
import com.ricardo.pessoa.Usuario;
import com.ricardo.pessoa.UsuarioSimples;
import com.ricardo.promptdecorators.PromptComMensagemErro;
import com.ricardo.servicos.AutenticacaoServiceImpl;
import com.ricardo.servicos.PromptServiceImpl;
import com.ricardo.servicos.UsuarioServiceImpl;
import com.ricardo.util.ShaHash;
import com.ricardo.util.UserInput;
import com.ricardo.validacao.PreDefinedValidators;

import java.sql.Connection;
import java.util.Objects;

/**
 * Created by ricardo on 27/05/16.
 * Classe responsável pela tela de login de usuário.
 */
public class Login implements Tela {
    private PromptService promptService;
    private AutenticacaoService autenticacaoService;
    private UsuarioService usuarioService;
    private GerenciamentoDeTela gerenciamentoDeTela;

    public Login(PromptService p, AutenticacaoService aS, UsuarioService u, GerenciamentoDeTela gT) {
        this.promptService = Objects.requireNonNull(p, this.getClass().getName() + ": PromptService nulo.");
        this.autenticacaoService = Objects.requireNonNull(aS, this.getClass().getName() + ": AutenticacaoService nulo.");
        this.usuarioService = Objects.requireNonNull(u, this.getClass().getName() + ": UsuarioService nulo.");
        this.gerenciamentoDeTela = Objects.requireNonNull(gT, this.getClass().getName() + ": GerenciamentoDeTela nulo.");
    }

    @Override
    public void iniciarTela() {
        Usuario usuario = new UsuarioSimples();
        this.promptService = new PromptComMensagemErro(this.promptService);
        this.promptService.setValidador(PreDefinedValidators.getValidadorLogin());

        System.out.println("------------Login de usuário------------");

        do {
            usuario.setLogin(this.promptService.getUserData("Usuário: "));
            usuario.setHashSenha(ShaHash.strToSha256(this.promptService.getUserData("Senha: ")));
            boolean passou = autenticacaoService.autenticarUsuario(usuario);

            if (!passou) {
                System.out.println("Usuário ou senha inválidos");
            } else {
                usuario = usuarioService.getUsuarioPorLogin(usuario.getLogin());

                if (usuario.isAdmin()) {
                    gerenciamentoDeTela.setTela(new MenuAdmin(usuario, new PromptServiceImpl(new UserInput())));
                } else {
                    gerenciamentoDeTela.setTela(new MenuUsuario(usuario, new PromptServiceImpl(new UserInput())));
                }

                gerenciamentoDeTela.exibirTela();
                break;
            }
        } while (true);
    }
}
