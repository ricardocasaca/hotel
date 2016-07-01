package com.ricardo.erro;

import com.ricardo.interfaces.StatusErro;

/**
 * Created by ricardo on 09/06/16.
 */
public abstract class ErrorStatusHandler implements StatusErro {
    private String codigo;

    public ErrorStatusHandler(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getCodigo() {
        return this.codigo;
    }

    @Override
    public boolean isOK() {
        return this.codigo.equals("0") ? true : false;
    }
}
