package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.Tela;
import com.ricardo.pessoa.Usuario;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by ricardo on 14/06/16.
 */
public class MenuAdminTest {
    // TODO: Não da pra testar. A tela deveria ser responsável apenas por mostrar o menu e não construí-lo.
    @Test
    public void iniciarTela() throws Exception {
        Usuario u = mock(Usuario.class);
        PromptService p = mock(PromptService.class);
        when(p.getUserData(anyString())).thenReturn("teste");
        Tela menuAdmin = new MenuAdmin(u, p);

        menuAdmin.iniciarTela();
        verify(p).getUserData(anyString());
    }

}