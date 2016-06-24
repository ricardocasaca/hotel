package com.ricardo.menu;

import com.ricardo.interfaces.OperacaoMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ricardo on 03/06/16.
 * Classe responsável por representar um Menu.
 */
public class MenuImpl implements com.ricardo.interfaces.Menu {
    private Map<String, ItemMenu> items = new HashMap<>();

    /**
     * Executa a operação referenciada pelo item de menu mapeado pela
     * opção indicada.
     *
     * @param opcao Inteiro que identifica o item de menu desejado.
     * @return true se alguma operação for executada. false se a opção for 0.
     * Obs: 0 é utilizado para sair do menu e indica que nenhuma operação deve ser realizada.
     */
    @Override
    public boolean executarOpcao(String opcao) {
        // Retorna zero como sinal para sair do menu.
        if (opcao.equals("0"))
            return false;

        ItemMenu iM = this.items.get(opcao); // TODO: Fazer uma checagem aqui. Se retornar nulo não deve continuar.
        iM.executar();
        return true;
    }

    /**
     * Adiciona um novo item no menu.
     *
     * @param key       Inteiro que identifica o novo item de menu.
     * @param descricao String contendo a descrição do novo item de menu.
     * @param f         Referência para a operação a ser realizada por este item de menu.
     * @return Objeto ItemMenu adicionado.
     */
    @Override
    public ItemMenu addItemMenu(String key, String descricao, OperacaoMenu f) {
        ItemMenu iM = new ItemMenu(descricao, f);
        return this.items.put(key, iM);
    }

    /**
     * Adiciona um novo item no menu.
     *
     * @param key Inteiro que identifica o novo item de menu.
     * @param iM  Objeto ItemMenu.
     * @return Objeto ItemMenu adicionado.
     */
    @Override
    public ItemMenu addItemMenu(String key, ItemMenu iM) {
        return this.items.put(key, iM);
    }

    /**
     * Remove o item de menu indicado.
     *
     * @param key Inteiro que identifica o item de menu a ser removido.
     * @return Objeto do tipo ItemMenu removido.
     */
    @Override
    public ItemMenu removeItemMenu(String key) {
        return this.items.remove(key);
    }

    /**
     * Mostra o menu completo na tela.
     */
    @Override
    public void mostrarMenu() {
        for (String key : this.items.keySet()) {
            System.out.println(key + " - " + this.items.get(key).getDescricao());
        }
    }
}
