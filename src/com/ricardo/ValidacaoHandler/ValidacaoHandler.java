package com.ricardo.ValidacaoHandler;

import com.ricardo.interfaces.StatusErro;
import com.ricardo.interfaces.Validador;

/**
 * Created by ricardo on 09/06/16.
 */
public abstract class ValidacaoHandler implements Validador {
    protected ValidacaoHandler refProximo; // Aponta para o próximo validador da cadeia.

    @Override
    public StatusErro validar(String s) {
        return null;
    }

    public void add(ValidacaoHandler v) {
        if (this.refProximo == null) {
            this.refProximo = v;
        } else {
            this.refProximo.add(v);
        }
    }
}
