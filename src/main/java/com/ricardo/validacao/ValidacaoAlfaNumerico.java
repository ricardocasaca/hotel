package com.ricardo.validacao;

import com.ricardo.ValidacaoHandler.ValidacaoHandler;
import com.ricardo.interfaces.StatusErro;

/**
 * Created by ricardo on 02/06/16.
 */
public class ValidacaoAlfaNumerico extends ValidacaoHandler {
    @Override
    public StatusErro validar(String s) {
        if (s.matches("^[a-zA-Z0-9]*$")) {
            if (this.refProximo != null)
                this.refProximo.validar(s);
        } else {
            return new ValidacaoStatus("validacao.alfanumerico.naoealfanumerico");
        }

        return new ValidacaoStatus("0");
    }
}
