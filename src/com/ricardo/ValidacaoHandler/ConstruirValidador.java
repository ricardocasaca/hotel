package com.ricardo.ValidacaoHandler;

import com.ricardo.interfaces.Validador;

/**
 * Created by ricardo on 02/06/16.
 */
public class ConstruirValidador {
    private ValidacaoHandler ptInicio; // Ponteiro para o primeiro validador criado.

    public ConstruirValidador addValidador(ValidacaoHandler v) {
        if (this.ptInicio == null) {
            this.ptInicio = v;
        } else {
            this.ptInicio.add(v);
        }

        return this;
    }

    public Validador construir() {
        return this.ptInicio;
    }
}
