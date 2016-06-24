package com.ricardo.main;

import com.ricardo.conexao.ConexaoHandlerHolder;
import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.interfaces.*;
import com.ricardo.pessoa.Usuario;
import com.ricardo.servicos.AutenticacaoServiceImpl;
import com.ricardo.servicos.CriarTabelasServiceImpl;
import com.ricardo.servicos.PromptServiceImpl;
import com.ricardo.servicos.UsuarioServiceImpl;
import com.ricardo.tela.CadastroUsuario;
import com.ricardo.tela.GerenciamentoDeTela;
import com.ricardo.tela.Login;
import com.ricardo.util.UserInput;
import com.ricardo.validacao.PreDefinedValidators;

import java.io.File;

class Main {

    public static void main(String[] args) {
        EntityManagerFactorySingleton.getInstance();
        /*
        File dbFile = new File("hotel.db");

        // Se o arquivo da base de dados não existir, cria uma nova.
        if (!dbFile.exists()) {
            CriarTabelasService tabelasService = new CriarTabelasServiceImpl(ConexaoHandlerHolder.getInstance().getSqliteConHandler());
            tabelasService.criarTabelas();
        }
        */

        UsuarioService usuarioService = new UsuarioServiceImpl(ConexaoHandlerHolder.getInstance().getSqliteConHandler());

        // Se não houver pelo menos 1 admin cadastrado, abre menu para cadastrar.
        if (!usuarioService.existeAdmin()) {
            System.out.println("Nenhum usuário administrador cadastrado na base. É preciso cadastrar pelo menos 1 administrador\n");
            PromptService p = new PromptServiceImpl(new UserInput());
            GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new CadastroUsuario(p, usuarioService));
            gerenciamentoDeTela.exibirTela();
        }

        PromptService promptService = new PromptServiceImpl(new UserInput());
        ConexaoHandler conexaoHandler = ConexaoHandlerHolder.getInstance().getSqliteConHandler();
        AutenticacaoService autenticacaoService = new AutenticacaoServiceImpl(conexaoHandler, new UsuarioServiceImpl(conexaoHandler));
        UsuarioService uS = new UsuarioServiceImpl(conexaoHandler);
        GerenciamentoDeTela gT = new GerenciamentoDeTela();

        GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new Login(promptService, autenticacaoService, uS, gT));
        gerenciamentoDeTela.exibirTela();
    }
}
