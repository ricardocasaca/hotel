package com.ricardo.validacao;

import com.ricardo.ValidacaoHandler.ValidacaoHandler;
import com.ricardo.interfaces.StatusErro;

/**
 * Created by ricardo on 30/05/16.
 * A ideia desta classe é fazer a validação mínima necessária em todos os inputs de usuário.
 * Como por exemplo limitar o tamanho máximo de entrada de dados permitida.
 */
public class ValidacaoTamanhoInput extends ValidacaoHandler {
    private final long tamanho;

    public ValidacaoTamanhoInput(long tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public StatusErro validar(String s) {
        if ((s == null) || (s.length() <= this.tamanho)) {
            if (this.refProximo != null)
                return this.refProximo.validar(s);
        } else {
            return new ValidacaoStatus("validacao.tamanhoinput.excedido");
        }

        return new ValidacaoStatus("0");
    }
}
