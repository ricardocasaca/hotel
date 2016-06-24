package com.ricardo.validacao;

import com.ricardo.ValidacaoHandler.ValidacaoHandler;
import com.ricardo.interfaces.StatusErro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ricardo on 02/06/16.
 */
public class ValidacaoDataFutura extends ValidacaoHandler {
    @Override
    public StatusErro validar(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        // Faz com que o formato da data deva ser estritamente dd/MM/aaaa.
        formatter.setLenient(false);

        try {
            Date dataUsuario = formatter.parse(s);
            Date dataAtual = new Date(); // Instancia com a data atual por padrão.

            // Se validar, passa pro próximo validador da corrente.
            if (dataUsuario.after(dataAtual)) {
                if (this.refProximo != null)
                    return this.refProximo.validar(s);
            } else {
                return new ValidacaoStatus("validacao.data.anterioraopresente");
            }
        } catch (ParseException e) {
            return new ValidacaoStatus("validacao.data.formatoinvalido");
        }

        return new ValidacaoStatus("0"); // Se o formato for válido (dd/MM/aaaa).
    }
}
