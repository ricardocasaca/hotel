package com.ricardo.promptdecorators;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.Validador;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ricardo on 31/05/16.
 */
public class PromptComMensagemErroTest {
    private String ValidacaoStatus; // Se for 0 é porque validou corretamente.
    private PromptService promptService;

    /**
     * Quando o input é válido retorna exatamente o que o foi recebido no prompt.
     *
     * @throws Exception
     */
    @Test
    public void getUserData() throws Exception {
        String testInput;
        this.ValidacaoStatus = "0";
        testInput = this.promptService.getUserData("Mensagem irrelevante");
        assertEquals("foi validado!", testInput);
    }

    @Before
    public void setUp() throws Exception {
        this.promptService = new PromptService() {
            @Override
            public String getUserData(String texto) {
                return "foi validado!";
            }

            @Override
            public void setValidador(Validador v) {

            }
        };

        this.promptService = new PromptComMensagemErro(this.promptService);
    }
}