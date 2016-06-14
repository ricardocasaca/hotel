package com.ricardo.validacao;

import com.ricardo.ValidacaoHandler.ValidacaoHandler;
import com.ricardo.interfaces.StatusErro;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by ricardo on 02/06/16.
 */
public class ValidacaoDataFormato extends ValidacaoHandler {
    @Override
    public StatusErro validar(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        // Faz com que o formato da data deva ser estritamente dd/MM/aaaa.
        formatter.setLenient(false);

        try {
            formatter.parse(s);

            if (this.refProximo != null)
                return this.refProximo.validar(s);
        } catch (ParseException e) {
            return new ValidacaoStatus("validacao.data.formatoinvalido");
        }

        return new ValidacaoStatus("0"); // Se o formato for válido (dd/MM/aaaa).
    }
}
