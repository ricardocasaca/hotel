package com.ricardo.interfaces;

import com.ricardo.menu.ItemMenu;

/**
 * Created by ricardo on 03/06/16.
 */
public interface Menu {
    boolean executarOpcao(String opcao);

    ItemMenu addItemMenu(String key, String descricao, OperacaoMenu f);

    ItemMenu addItemMenu(String key, ItemMenu iM);

    ItemMenu removeItemMenu(String key);

    void mostrarMenu();
}
