package com.ricardo.validacao;

import com.ricardo.ValidacaoHandler.ValidacaoHandler;
import com.ricardo.interfaces.StatusErro;

/**
 * Created by ricardo on 02/06/16.
 */
public class ValidacaoIntMenorQue extends ValidacaoHandler {
    private long limite;

    public ValidacaoIntMenorQue(long limite) {
        this.limite = limite;
    }

    @Override
    public StatusErro validar(String s) {
        double numeroConvertido;

        try {
            numeroConvertido = Long.parseLong(s);
        } catch (NumberFormatException e) {
            return new ValidacaoStatus("validacao.intmenorque.naoenumero");
        }

        if (numeroConvertido <= this.limite) {
            if (this.refProximo != null)
                return this.refProximo.validar(s);
        } else {
            return new ValidacaoStatus("validacao.intmenorque.ultrapassoulimite");
        }

        return new ValidacaoStatus("0");
    }
}
