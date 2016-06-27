package com.ricardo.funcoesmenu;

import com.ricardo.conexao.ConexaoHandlerHolder;
import com.ricardo.interfaces.OperacaoMenu;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.QuartoService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.pessoa.Usuario;
import com.ricardo.servicos.PromptServiceImpl;
import com.ricardo.servicos.QuartoServiceImpl;
import com.ricardo.servicos.ReservaServiceImpl;
import com.ricardo.tela.GerenciamentoDeTela;
import com.ricardo.util.UserInput;
import com.ricardo.validacao.PreDefinedValidators;

import java.util.Objects;

/**
 * Created by ricardo on 05/06/16.
 * Classe responsável por inicializar a tela que efetua a operação
 * de cadastrar uma reserva.
 */
public class CadastroReserva implements OperacaoMenu {
    private Usuario usuario;

    /**
     * Inicializa um CadastroReserva.
     *
     * @param usuario Usuário para o qual a reserva será cadastrada.
     */
    public CadastroReserva(Usuario usuario) {
        this.usuario = Objects.requireNonNull(usuario);
    }

    @Override
    public void executar() {
        PromptService p = new PromptServiceImpl(new UserInput());
        QuartoService qS = new QuartoServiceImpl();
        ReservaService rS = new ReservaServiceImpl();
        GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new com.ricardo.tela.CadastroReserva(this.usuario, p, qS, rS));
        gerenciamentoDeTela.exibirTela();
    }
}
