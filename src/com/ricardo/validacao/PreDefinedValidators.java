package com.ricardo.validacao;

import com.ricardo.ValidacaoHandler.ConstruirValidador;
import com.ricardo.interfaces.Validador;

/**
 * Created by ricardo on 02/06/16.
 */
public class PreDefinedValidators {
    /**
     * Prepara um validador específico para quarto.
     *
     * @return
     */
    public static Validador getValidadorQuarto() {
        return new ConstruirValidador().addValidador(new ValidacaoNulo())
                .addValidador(new ValidacaoVazio())
                .addValidador(new ValidacaoIntMenorQue(9999))
                .addValidador(new ValidacaoIntNaoNegativo())
                .construir();
    }

    /**
     * Prepara um validador específico para login.
     *
     * @return
     */
    public static Validador getValidadorLogin() {
        return new ConstruirValidador().addValidador(new ValidacaoNulo())
                .addValidador(new ValidacaoVazio())
                .addValidador(new ValidacaoEspaco())
                .addValidador(new ValidacaoAlfaNumerico())
                .construir();
    }
}
