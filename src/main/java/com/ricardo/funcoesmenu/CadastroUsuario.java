package com.ricardo.funcoesmenu;

import com.ricardo.interfaces.OperacaoMenu;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.servicos.PromptServiceImpl;
import com.ricardo.servicos.UsuarioServiceImpl;
import com.ricardo.tela.GerenciamentoDeTela;
import com.ricardo.util.UserInput;

/**
 * Created by ricardo on 05/06/16.
 * Classe responsável por iniciar a tela que realiza a operação de cadastrar
 * um novo usuario.
 */
public class CadastroUsuario implements OperacaoMenu {
    @Override
    public void executar() {
        PromptService p = new PromptServiceImpl(new UserInput());
        UsuarioService uS = new UsuarioServiceImpl();
        GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new com.ricardo.tela.CadastroUsuario(p, uS));
        gerenciamentoDeTela.exibirTela();
    }
}
