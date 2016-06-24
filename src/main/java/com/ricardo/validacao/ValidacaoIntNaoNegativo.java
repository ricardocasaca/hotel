package com.ricardo.validacao;

import com.ricardo.ValidacaoHandler.ValidacaoHandler;
import com.ricardo.interfaces.StatusErro;

/**
 * Created by ricardo on 02/06/16.
 */
public class ValidacaoIntNaoNegativo extends ValidacaoHandler {
    @Override
    public StatusErro validar(String s) {
        // Garante que o número seja um inteiro não negativo.
        if (s.matches("\\d+")) {
            if (this.refProximo != null)
                return this.refProximo.validar(s);
        } else {
            return new ValidacaoStatus("validacao.intnaonegativo.intnaonegativo");
        }

        return new ValidacaoStatus("0");
    }
}
