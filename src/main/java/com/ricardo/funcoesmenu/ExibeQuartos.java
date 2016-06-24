package com.ricardo.funcoesmenu;

import com.ricardo.conexao.ConexaoHandlerHolder;
import com.ricardo.interfaces.OperacaoMenu;
import com.ricardo.interfaces.QuartoService;
import com.ricardo.servicos.QuartoServiceImpl;
import com.ricardo.suites.Quarto;
import com.ricardo.tela.GerenciamentoDeTela;

import java.util.List;

/**
 * Created by ricardo on 05/06/16.
 * Classe responsável por realizar a operação de exibir os quartos
 * cadastrados no sistema.
 */
public class ExibeQuartos implements OperacaoMenu {
    @Override
    public void executar() {
        QuartoService quartoService = new QuartoServiceImpl(ConexaoHandlerHolder.getInstance().getSqliteConHandler());
        List<Quarto> quartos = quartoService.getQuartos();
        GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new com.ricardo.tela.ExibeQuartos(quartos));
        gerenciamentoDeTela.exibirTela();
    }
}