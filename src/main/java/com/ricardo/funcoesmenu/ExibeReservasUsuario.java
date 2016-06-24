package com.ricardo.funcoesmenu;

import com.ricardo.conexao.ConexaoHandlerHolder;
import com.ricardo.interfaces.OperacaoMenu;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.pessoa.Usuario;
import com.ricardo.servicos.ReservaServiceImpl;
import com.ricardo.tela.GerenciamentoDeTela;

/**
 * Created by ricardo on 05/06/16.
 * Classe responsável por realizar a operação de exibir todas as reservas efetuadas
 * por um dado usuário.
 */
public class ExibeReservasUsuario implements OperacaoMenu {
    private Usuario usuario;

    public ExibeReservasUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void executar() {
        ReservaService rS = new ReservaServiceImpl(ConexaoHandlerHolder.getInstance().getSqliteConHandler());
        GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new com.ricardo.tela.ExibeReservasUsuario(this.usuario, rS));
        gerenciamentoDeTela.exibirTela();
    }
}
