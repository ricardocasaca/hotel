package com.ricardo.validacao;

import com.ricardo.ValidacaoHandler.ValidacaoHandler;
import com.ricardo.interfaces.StatusErro;

/**
 * Created by ricardo on 02/06/16.
 */
public class ValidacaoNulo extends ValidacaoHandler {
    @Override
    public StatusErro validar(String s) {
        if (s != null) {
            if (this.refProximo != null)
                return this.refProximo.validar(s);
        } else {
            return new ValidacaoStatus("validacao.validacaonulo.inputnulo");
        }

        return new ValidacaoStatus("0");
    }
}
