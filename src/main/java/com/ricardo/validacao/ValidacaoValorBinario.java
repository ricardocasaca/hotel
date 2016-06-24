package com.ricardo.validacao;

import com.ricardo.ValidacaoHandler.ValidacaoHandler;
import com.ricardo.interfaces.StatusErro;

/**
 * Created by ricardo on 25/05/16.
 */
public class ValidacaoValorBinario extends ValidacaoHandler {
    @Override
    public StatusErro validar(String s) {
        if (s == null)
            return new ValidacaoStatus("1004E");

        if (s.matches("[0, 1]")) {
            if (this.refProximo != null)
                return this.refProximo.validar(s);
        } else {
            return new ValidacaoStatus("validacao.valorbinario.valornaobinario");
        }

        return new ValidacaoStatus("0");
    }
}
