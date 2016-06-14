package com.ricardo.validacao;

import com.ricardo.erro.ErrorStatusHandler;

import java.util.Objects;

/**
 * Created by ricardo on 27/05/16.
 */
public class ValidacaoStatus extends ErrorStatusHandler {
    public ValidacaoStatus(String codigo) {
        super(Objects.requireNonNull(codigo, "Código de erro nulo no construtor de ValidacaoStatus."));
    }
}
