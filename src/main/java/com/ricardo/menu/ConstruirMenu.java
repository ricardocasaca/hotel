package com.ricardo.menu;

import com.ricardo.interfaces.Menu;
import com.ricardo.interfaces.OperacaoMenu;

/**
 * Created by ricardo on 03/06/16.
 * Classe builder utilizada para construir um menu de forma dinâmica em tempo de execução.
 */
public class ConstruirMenu {
    private Menu menu;

    /**
     * Inicializa um ConstruirMenu instanciando um novo menu.
     */
    public ConstruirMenu() {
        this.menu = new MenuImpl();
    }

    /**
     * Adiciona um novo item no menu.
     *
     * @param key Inteiro responsável por identificar a opção de menu adicionada.
     * @param iM  Objeto ItemMenu.
     * @return Objeto ConstruirMenu.
     */
    public ConstruirMenu addItemMenu(String key, ItemMenu iM) {
        this.menu.addItemMenu(key, iM);
        return this;
    }

    /**
     * Adiciona um novo item no menu.
     *
     * @param key       Inteiro responsável por identificar a opção de menu adicionada.
     * @param descricao String contendo a descrição do item.
     * @param operacao  Objeto OperacaoMenu representando a operação de menu a ser executada.
     * @return Objeto ConstruirMenu.
     */
    public ConstruirMenu addItemMenu(String key, String descricao, OperacaoMenu operacao) {
        this.menu.addItemMenu(key, descricao, operacao);
        return this;
    }

    /**
     * Retorna o menu construido.
     *
     * @return Menu construido.
     */
    public Menu construir() {
        return this.menu;
    }
}
