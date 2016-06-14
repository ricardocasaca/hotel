package com.ricardo.menu;

import com.ricardo.interfaces.Menu;
import com.ricardo.interfaces.OperacaoMenu;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by ricardo on 12/06/16.
 */
public class MenuImplTest {
    private Menu menu;

    @Before
    public void setUp() throws Exception {
        this.menu = new ConstruirMenu().addItemMenu("1", mock(ItemMenu.class)).addItemMenu("2", mock(ItemMenu.class)).construir();
    }

    @Test
    public void seExecutouOpcaoDeveRetornarTrue() throws Exception {
        assertTrue(this.menu.executarOpcao("1"));
    }

    @Test
    public void executarOpcaoDeveRetornarFalseSeOpcaoForZero() throws Exception {
        assertFalse(this.menu.executarOpcao("0"));
    }

    @Test
    public void adicionaItemDeMenuNoMenuDeveRetornarNull() throws Exception {
        assertNull(this.menu.addItemMenu("key", "descricao", mock(OperacaoMenu.class))); // Deve retornar null, pois não havia nenhum elemento associado com a chave key antes.
    }

    @Test
    public void adicionaObjetoItemDeMenuNoMenuDeveRetornarNull() throws Exception {
        ItemMenu iM = mock(ItemMenu.class);
        assertNull(this.menu.addItemMenu("key", iM)); // Deve retornar null, pois não havia nenhum elemento associado com a chave key antes.
    }

    @Test
    public void removeItemMenuDeveRetornarNull() throws Exception {
        assertThat(this.menu.removeItemMenu("1"), instanceOf(ItemMenu.class)); // Deve retornar null, pois não havia nenhum item de menu associado com essa key antes.
    }
}