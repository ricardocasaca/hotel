package com.ricardo.validacao;

import com.ricardo.interfaces.StatusErro;
import com.ricardo.interfaces.Validador;

/**
 * Created by ricardo on 13/06/16.
 */
public class SemValidacao implements Validador {
    @Override
    public StatusErro validar(String s) {
        return new ValidacaoStatus("0");
    }
}
