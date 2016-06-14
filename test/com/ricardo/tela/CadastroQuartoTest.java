package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.QuartoService;
import com.ricardo.interfaces.Tela;
import com.ricardo.suites.Quarto;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Kennedy Oliveira
 */
public class CadastroQuartoTest {
    private Tela cadastroQuarto;
    private PromptService promptService;
    private QuartoService quartoService;

    @Before
    public void setUp() throws Exception {
        this.promptService = mock(PromptService.class);
        this.quartoService = mock(QuartoService.class);
        this.cadastroQuarto = new CadastroQuarto(this.promptService, this.quartoService);
    }

    @Test
    public void verificaSeUsouPromptEQuartoService() throws Exception {
        this.cadastroQuarto.iniciarTela();
        verify(this.promptService, times(1)).getUserData(anyString());
        verify(this.quartoService, times(1)).cadastrarQuarto(any(Quarto.class));
    }
}