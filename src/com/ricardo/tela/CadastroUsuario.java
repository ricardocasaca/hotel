package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.Tela;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.pessoa.Usuario;
import com.ricardo.pessoa.UsuarioSimples;
import com.ricardo.promptdecorators.PromptComMensagemErro;
import com.ricardo.servicos.PromptServiceImpl;
import com.ricardo.util.ShaHash;
import com.ricardo.util.UserInput;
import com.ricardo.validacao.*;

import java.util.Objects;

/**
 * Created by ricardo on 20/05/16.
 * Classe responsável pela tela que efetua o cadastro de usuário.
 */
public class CadastroUsuario implements Tela {
    private PromptService promptService;
    private UsuarioService usuarioService;

    public CadastroUsuario(PromptService promptService, UsuarioService usuarioService) {
        this.promptService = Objects.requireNonNull(promptService, "PromptService nulo no construtor de CadastroUsuario.");
        this.usuarioService = Objects.requireNonNull(usuarioService, "UsuarioService nulo no construtor de CadastroUsuario.");
    }

    /**
     * Inicia tela.
     */
    @Override
    public void iniciarTela() {
        Usuario usuario = new UsuarioSimples();
        promptService = new PromptComMensagemErro(promptService);

        promptService.setValidador(new ValidacaoVazio());
        usuario.setNome(promptService.getUserData("Digite o nome completo do usuário: "));

        promptService.setValidador(PreDefinedValidators.getValidadorLogin());
        usuario.setLogin(promptService.getUserData("Digite um login para o usuário (apenas letras e/ou números inteiros): "));

        /*
        Deve haver sempre um administrador cadastrado. Se não houver, o seguinte
        if força que seja.
         */
        boolean isAdmin;

        if (!usuarioService.existeAdmin()) {
            promptService.setValidador(new ValidacaoValorBinario());
            isAdmin = promptService.getUserData("O usuário \"" + usuario.getLogin() + "\" é administrador? (1 Sim, 0 Não): ").equals("1");
        } else {
            isAdmin = true;
        }

        promptService.setValidador(new SemValidacao());
        usuario.setHashSenha(ShaHash.strToSha256(promptService.getUserData("Digite uma senha para o usuário: ")));
        usuario.setAdmin(isAdmin);
        usuarioService.cadastrarUsuario(usuario);

        System.out.println("Cadastrado com sucesso!");
    }
}
