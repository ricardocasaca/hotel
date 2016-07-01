package com.ricardo.funcoesmenu;

import com.ricardo.conexao.EntityManagerFactoryFacadeImpl;
import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.interfaces.OperacaoMenu;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.QuartoService;
import com.ricardo.promptdecorators.PromptComMensagemErro;
import com.ricardo.servicos.PromptServiceImpl;
import com.ricardo.servicos.QuartoServiceImpl;
import com.ricardo.tela.GerenciamentoDeTela;
import com.ricardo.util.UserInput;
import com.ricardo.validacao.PreDefinedValidators;

/**
 * Created by ricardo on 03/06/16.
 * Classe responsável por iniciar a tela que realiza a operação de cadastrar
 * um novo quarto.
 */
public class CadastroQuarto implements OperacaoMenu {
    public void executar() {
        PromptService promptService = new PromptServiceImpl(PreDefinedValidators.getValidadorQuarto(), new UserInput());
        promptService = new PromptComMensagemErro(promptService);
        QuartoService quartoService = new QuartoServiceImpl(new EntityManagerFactoryFacadeImpl(EntityManagerFactorySingleton.getInstance().getEntityManagerFactory()));
        GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new com.ricardo.tela.CadastroQuarto(promptService, quartoService));
        gerenciamentoDeTela.exibirTela();
    }
}
