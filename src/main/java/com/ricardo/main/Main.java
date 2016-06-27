package com.ricardo.main;

import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.interfaces.AutenticacaoService;
import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.servicos.AutenticacaoServiceImpl;
import com.ricardo.servicos.PromptServiceImpl;
import com.ricardo.servicos.UsuarioServiceImpl;
import com.ricardo.tela.CadastroUsuario;
import com.ricardo.tela.GerenciamentoDeTela;
import com.ricardo.tela.Login;
import com.ricardo.util.UserInput;

class Main {

    public static void main(String[] args) {
        EntityManagerFactorySingleton.getInstance();
        UsuarioService usuarioService = new UsuarioServiceImpl();

        if (!existeAdminCadastrado(usuarioService)) {
            System.out.println("Nenhum usuário administrador cadastrado na base. É preciso cadastrar pelo menos 1 administrador\n");
            cadastraUsuarioAdmin();
        }

        PromptService promptService = new PromptServiceImpl(new UserInput());
        AutenticacaoService autenticacaoService = new AutenticacaoServiceImpl(usuarioService);
        GerenciamentoDeTela gT = new GerenciamentoDeTela();

        GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new Login(promptService, autenticacaoService, usuarioService, gT));
        gerenciamentoDeTela.exibirTela();

        System.exit(0);
    }

    public static boolean existeAdminCadastrado(UsuarioService uS){
        return uS.existeAdmin();
    }

    public static void cadastraUsuarioAdmin(){
        new com.ricardo.funcoesmenu.CadastroUsuario().executar();
    }
}
