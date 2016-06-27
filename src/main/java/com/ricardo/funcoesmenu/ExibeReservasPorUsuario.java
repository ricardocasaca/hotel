package com.ricardo.funcoesmenu;

import com.ricardo.interfaces.OperacaoMenu;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.servicos.PromptServiceImpl;
import com.ricardo.servicos.ReservaServiceImpl;
import com.ricardo.servicos.UsuarioServiceImpl;
import com.ricardo.tela.GerenciamentoDeTela;
import com.ricardo.util.UserInput;

/**
 * Created by ricardo on 05/06/16.
 * Classe responsável por realizar a operação de exibir todas as reservas
 * para um dado usuário.
 */
public class ExibeReservasPorUsuario implements OperacaoMenu {
    PromptService p = new PromptServiceImpl(new UserInput());
    ReservaService rS = new ReservaServiceImpl();
    UsuarioService uS = new UsuarioServiceImpl();

    @Override
    public void executar() {
        GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new com.ricardo.tela.ExibeReservasPorUsuario(uS, rS, p));
        gerenciamentoDeTela.exibirTela();
    }
}
