package com.ricardo.funcoesmenu;

import com.ricardo.conexao.EntityManagerFactoryFacadeImpl;
import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.interfaces.OperacaoMenu;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.QuartoService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.servicos.PromptServiceImpl;
import com.ricardo.servicos.QuartoServiceImpl;
import com.ricardo.servicos.ReservaServiceImpl;
import com.ricardo.tela.GerenciamentoDeTela;
import com.ricardo.util.UserInput;

/**
 * Created by ricardo on 05/06/16.
 * Classe responsável por realizar a operação de exibir todas as reservas
 * cadastradas para um dado quarto.
 */
public class ExibeReservasQuarto implements OperacaoMenu {
    @Override
    public void executar() {
        PromptService p = new PromptServiceImpl(new UserInput());
        ReservaService rS = new ReservaServiceImpl(new EntityManagerFactoryFacadeImpl(EntityManagerFactorySingleton.getInstance().getEntityManagerFactory()));
        QuartoService qS = new QuartoServiceImpl(new EntityManagerFactoryFacadeImpl(EntityManagerFactorySingleton.getInstance().getEntityManagerFactory()));
        GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new com.ricardo.tela.ExibeReservasQuarto(rS, qS, p));
        gerenciamentoDeTela.exibirTela();
    }
}
