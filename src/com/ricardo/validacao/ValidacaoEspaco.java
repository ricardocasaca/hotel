package com.ricardo.validacao;

import com.ricardo.ValidacaoHandler.ValidacaoHandler;
import com.ricardo.interfaces.StatusErro;

/**
 * Created by ricardo on 02/06/16.
 */
public class ValidacaoEspaco extends ValidacaoHandler {
    @Override
    public StatusErro validar(String s) {
        if (!s.contains(" ")) {
            if (this.refProximo != null)
                this.refProximo.validar(s);
        } else {
            return new ValidacaoStatus("validacao.espaco.naopermitido");
        }

        return new ValidacaoStatus("0");
    }
}
