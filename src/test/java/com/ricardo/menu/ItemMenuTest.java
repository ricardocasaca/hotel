package com.ricardo.menu;

import com.ricardo.interfaces.OperacaoMenu;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by ricardo on 12/06/16.
 */
public class ItemMenuTest {
    @Test
    public void getDescricao() throws Exception {
        ItemMenu iM = new ItemMenu("teste", mock(OperacaoMenu.class));
        assertEquals("teste", iM.getDescricao());
    }

    @Test
    public void executar() throws Exception {
        OperacaoMenu oM = mock(OperacaoMenu.class);
        ItemMenu iM = new ItemMenu("teste", oM);
        iM.executar();
        verify(oM).executar();
    }
}