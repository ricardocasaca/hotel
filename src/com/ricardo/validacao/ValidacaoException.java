package com.ricardo.validacao;

import com.ricardo.interfaces.StatusErro;

/**
 * Created by ricardo on 09/06/16.
 * Classe de exceção responsável por carregar o código de erro gerado
 * na falha da validação de input de usuário.
 */
public class ValidacaoException extends RuntimeException {
    private StatusErro erro;

    public ValidacaoException(StatusErro erro) {
        super("Erro de validação " + erro.getCodigo());
        this.erro = erro;
    }

    public ValidacaoException(StatusErro erro, String mensagem) {
        super(mensagem);
        this.erro = erro;
    }

    public StatusErro getErro() {
        return this.erro;
    }
}
