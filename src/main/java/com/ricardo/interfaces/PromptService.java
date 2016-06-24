package com.ricardo.interfaces;

import com.ricardo.validacao.ValidacaoException;

/**
 * Created by ricardo on 25/05/16.
 */
public interface PromptService {
    String getUserData(String texto) throws ValidacaoException;
    void setValidador(Validador v);
}
