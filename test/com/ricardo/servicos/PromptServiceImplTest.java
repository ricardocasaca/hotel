package com.ricardo.servicos;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.StatusErro;
import com.ricardo.interfaces.Validador;
import com.ricardo.util.UserInput;
import com.ricardo.validacao.ValidacaoException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ricardo on 13/06/16.
 */
public class PromptServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void semValidadorETamanhoDeInputNaoEspecificadoSeUltrapassarTamanhoPadraoDeInputDeveLancarException() throws Exception {
        this.thrown.expect(ValidacaoException.class);
        UserInput mockedUserInput = mock(UserInput.class);
        when(mockedUserInput.lerInputUsuario()).thenReturn("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"); // Texto maior que tamanho de input padrão.
        PromptService promptService = new PromptServiceImpl(mockedUserInput);
        promptService.getUserData("textoirrelevante");
    }

    @Test
    public void semValidadorETamanhoDeInputNaoEspecificadoSeNaoUltrapassarTamanhoPadraoDeInputDeveRetornarInputDeUsuario() throws Exception {
        UserInput mockedUserInput = mock(UserInput.class);
        when(mockedUserInput.lerInputUsuario()).thenReturn("xxx"); // Input do usuário.
        PromptService promptService = new PromptServiceImpl(mockedUserInput);
        String userInput = promptService.getUserData("textoirrelevante");
        assertEquals("xxx", userInput);
    }

    @Test
    public void semValidadorETamanhoDeInputEspecificadoSeUltrapassarTamanhoDeInputDeveLancarException() throws Exception {
        this.thrown.expect(ValidacaoException.class);
        UserInput mockedUserInput = mock(UserInput.class);
        when(mockedUserInput.lerInputUsuario()).thenReturn("xxxxxx"); // Texto maior que tamanho de input padrão.
        PromptService promptService = new PromptServiceImpl(5, mockedUserInput);
        promptService.getUserData("textoirrelevante");
    }

    @Test
    public void semValidadorETamanhoDeInputEspecificadoSeNaoUltrapassarTamanhoDeInputDeveRetornarInputDeUsuario() throws Exception {
        UserInput mockedUserInput = mock(UserInput.class);
        when(mockedUserInput.lerInputUsuario()).thenReturn("xxx"); // Texto menor que limite.
        PromptService promptService = new PromptServiceImpl(5, mockedUserInput);
        String userInput = promptService.getUserData("textoirrelevante");
        assertEquals("xxx", userInput);
    }

    @Test
    public void comValidadorEInputValidadoDeveRetornarInputDeUsuario() throws Exception {
        UserInput mockedUserInput = mock(UserInput.class);
        when(mockedUserInput.lerInputUsuario()).thenReturn("inputDoUsuario"); // Texto do usuário.
        Validador validador = mock(Validador.class);
        when(validador.validar(anyString())).thenReturn(new StatusErro() {
            @Override
            public String getCodigo() {
                return "0"; // Tudo OK.
            }

            @Override
            public boolean isOK() {
                return true; // Força input ser válido.
            }
        });
        PromptService promptService = new PromptServiceImpl(validador, mockedUserInput);
        String userInput = promptService.getUserData("textoirrelevante");
        assertEquals("inputDoUsuario", userInput);
    }

    @Test
    public void comValidadorEInputInvalidoDeveLancarException() throws Exception {
        this.thrown.expect(ValidacaoException.class);
        UserInput mockedUserInput = mock(UserInput.class);
        when(mockedUserInput.lerInputUsuario()).thenReturn("inputDoUsuario"); // Texto do usuário.
        Validador validador = mock(Validador.class);
        when(validador.validar(anyString())).thenReturn(new StatusErro() {
            @Override
            public String getCodigo() {
                return "diferenteDeZero"; // Tudo OK.
            }

            @Override
            public boolean isOK() {
                return false; // Força input ser inválido.
            }
        });
        PromptService promptService = new PromptServiceImpl(validador, mockedUserInput);
        promptService.getUserData("textoirrelevante");
    }
}