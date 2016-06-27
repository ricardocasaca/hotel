package com.ricardo.funcoesmenu;

import com.ricardo.interfaces.OperacaoMenu;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.servicos.UsuarioServiceImpl;
import com.ricardo.tela.GerenciamentoDeTela;

/**
 * Created by ricardo on 05/06/16.
 * Classe responsável por realizar a operação de exibir todos os usuários cadastrados
 * no sistema.
 */
public class ExibeUsuariosSistema implements OperacaoMenu {
    @Override
    public void executar() {
        UsuarioService uS = new UsuarioServiceImpl();
        GerenciamentoDeTela gerenciamentoDeTela = new GerenciamentoDeTela(new com.ricardo.tela.ExibeUsuariosSistema(uS));
        gerenciamentoDeTela.exibirTela();
    }
}
