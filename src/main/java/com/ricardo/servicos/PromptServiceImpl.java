package com.ricardo.servicos;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.StatusErro;
import com.ricardo.interfaces.Validador;
import com.ricardo.util.UserInput;
import com.ricardo.validacao.SemValidacao;
import com.ricardo.validacao.ValidacaoException;
import com.ricardo.validacao.ValidacaoStatus;
import com.ricardo.validacao.ValidacaoTamanhoInput;

import java.util.Objects;

/**
 * Created by ricardo on 24/05/16.
 * Classe responsável pelo serviço de prompt de usuário.
 */
public class PromptServiceImpl implements PromptService {
    private final UserInput userInput;
    private Validador validador;
    private final Validador vTamanhoInput; // Sempre é utilizado.

    public PromptServiceImpl(UserInput uI) {
        this(new SemValidacao(), 50, uI);
    }

    public PromptServiceImpl(int tamanhoInput, UserInput uI) {
        this(new SemValidacao(), tamanhoInput, uI);
    }

    public PromptServiceImpl(Validador validador, UserInput uI) {
        this(validador, 50, uI);
    }

    public PromptServiceImpl(Validador v, int tamanhoInput, UserInput uI) {
        this.validador = v;
        this.vTamanhoInput = new ValidacaoTamanhoInput(tamanhoInput);
        int tamanhoInput1 = tamanhoInput;
        this.userInput = uI;
    }

    @Override
    public String getUserData(String texto) throws ValidacaoException {
        StatusErro statusErro;
        System.out.println(texto);
        System.out.print("-> ");

        String userData;

        userData = this.userInput.lerInputUsuario();

        // Se o input não ultrapassou o tamanho máximo, continua com a validação.
        if (vTamanhoInput.validar(userData).getCodigo().equals("0")) {
            statusErro = this.validador.validar(userData);

            if (!statusErro.isOK())
                throw new ValidacaoException(statusErro);
        } else {
            statusErro = new ValidacaoStatus("validacao.tamanhoinput.excedido");
            throw new ValidacaoException(statusErro);
        }

        return userData;
    }

    @Override
    public void setValidador(Validador v) {
        this.validador = Objects.requireNonNull(v, this.getClass().getName() + ": validador nulo.");
    }
}
