package com.ricardo.funcoesmenu;

import com.ricardo.ValidacaoHandler.ConstruirValidador;
import com.ricardo.conexao.ConexaoHandlerHolder;
import com.ricardo.interfaces.OperacaoMenu;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.servicos.PromptServiceImpl;
import com.ricardo.servicos.ReservaServiceImpl;
import com.ricardo.tela.GerenciamentoDeTela;
import com.ricardo.util.UserInput;
import com.ricardo.validacao.ValidacaoDataFormato;

/**
 * Created by ricardo on 05/06/16.
 * Classe responsável por realizar a operação de exibir todas as reservas
 * cadastradas para uma data especificada.
 */
public class ExibeReservasData implements OperacaoMenu {
    @Override
    public void executar() {
        PromptService p = new PromptServiceImpl(new UserInput());
        ReservaService rS = new ReservaServiceImpl(ConexaoHandlerHolder.getInstance().getSqliteConHandler());

        GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new com.ricardo.tela.ExibeReservasData(p, rS));
        gerenciamentoDeTela.exibirTela();
    }
}
