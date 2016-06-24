package com.ricardo.validacao;

import com.ricardo.ValidacaoHandler.ValidacaoHandler;
import com.ricardo.interfaces.StatusErro;

/**
 * Created by ricardo on 25/05/16.
 */
public class ValidacaoVazio extends ValidacaoHandler {
    @Override
    public StatusErro validar(String s) {
        if (!s.isEmpty()) {
            if (this.refProximo != null)
                return this.refProximo.validar(s);
        } else {
            return new ValidacaoStatus("validacao.vazio.inputvazio");
        }

        return new ValidacaoStatus("0");
    }
}
