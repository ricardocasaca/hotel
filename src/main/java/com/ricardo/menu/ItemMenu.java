package com.ricardo.menu;

import com.ricardo.interfaces.OperacaoMenu;

import java.util.Objects;

/**
 * Created by ricardo on 03/06/16.
 * Classe que representa um item de menu. Contém a descrição e uma referência para
 * a operação a ser realizada.
 */
public class ItemMenu {
    private String descricao;
    private OperacaoMenu operacaoMenu;

    /**
     * Inicializa um ItemMenu.
     *
     * @param descricao    Descrição do item.
     * @param operacaoMenu Referência para a operação a ser efetuada.
     */
    public ItemMenu(String descricao, OperacaoMenu operacaoMenu) {
        this.descricao = Objects.requireNonNull(descricao, this.getClass().getName() + ": Parâmetro descricao nulo no construtor.");
        this.operacaoMenu = Objects.requireNonNull(operacaoMenu, this.getClass().getName() + ": Parâmetro operacaoMenu nulo no construtor.");
    }

    /**
     * Retorna uma String contendo a descrição para este item de menu.
     *
     * @return String com a descrição deste item de menu.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Executa a operação deste item de menu.
     */
    public void executar() {
        if (this.operacaoMenu != null)
            this.operacaoMenu.executar();
    }
}
