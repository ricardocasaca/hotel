package com.ricardo.main;

import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.interfaces.AutenticacaoService;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.servicos.AutenticacaoServiceImpl;
import com.ricardo.servicos.PromptServiceImpl;
import com.ricardo.servicos.UsuarioServiceImpl;
import com.ricardo.tela.GerenciamentoDeTela;
import com.ricardo.tela.Login;
import com.ricardo.util.UserInput;

class Main {

    public static void main(String[] args) {
        EntityManagerFactorySingleton.getInstance();
        cadastraAdminSeNaoHouver();
        exibeTelaDeLogin();

        System.exit(0);
    }

    public static boolean existeAdminCadastrado(){
        UsuarioService uS = new UsuarioServiceImpl();
        return uS.existeAdmin();
    }

    public static void cadastraUsuarioAdmin(){
        new com.ricardo.funcoesmenu.CadastroUsuario().executar();
    }

    public static void cadastraAdminSeNaoHouver(){
        if (!existeAdminCadastrado()) {
            System.out.println("Nenhum usuário administrador cadastrado na base. É preciso cadastrar pelo menos 1 administrador\n");
            cadastraUsuarioAdmin();
        }
    }

    public static void exibeTelaDeLogin(){
        UsuarioService uS = new UsuarioServiceImpl();
        PromptService pS = new PromptServiceImpl(new UserInput());
        AutenticacaoService aS = new AutenticacaoServiceImpl(uS);
        GerenciamentoDeTela gT = new GerenciamentoDeTela();
        GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new Login(pS, aS, uS, gT));
        gerenciamentoDeTela.exibirTela();
    }
}
