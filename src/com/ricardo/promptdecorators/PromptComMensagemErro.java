package com.ricardo.promptdecorators;

import com.ricardo.erro.PropertiesErroSingleton;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.Validador;
import com.ricardo.validacao.ValidacaoException;

import java.util.Objects;
import java.util.logging.Level;

/**
 * Created by ricardo on 29/05/16.
 * <p>
 * Esse decorator acrescenta o recurso de mensagem de erro ao prompt de usuário.
 * Cada vez que uma validação falha, é exibida uma mensagem explicativa na tela.
 */
public class PromptComMensagemErro extends PromptDecorator {
    private final PromptService promptService;

    /**
     * Inicializa um PromptComMensagemErro.
     *
     * @param p Objeto prompt a ser decorado com o recurso de mensagem de erro.
     */
    public PromptComMensagemErro(PromptService p) {this.promptService = p;}

    /**
     * Pega input do usuário.
     *
     * @param texto String contendo um texto descrevendo qual o input o usuário deve fornecer.
     * @return String contendo o input fornecido pelo usuário.
     */
    @Override
    public String getUserData(String texto) {
        String userInput = "";
        boolean ok = true;

        do {
            try {
                userInput = promptService.getUserData(texto);
            } catch (ValidacaoException e) {
                ok = false;
                log.log(Level.SEVERE, PropertiesErroSingleton.getInstance().getProp().getString(e.getErro().getCodigo())); // PropertiesErro.getMensagemErro(e.getErro().getCodigo())
                //System.out.println(PropertiesErro.getMensagemErro(e.getErro().getCodigo())); // TODO: Não é pra printar na tela porque é custoso mas essa merda de log não é flexivel
            }
        } while (!ok);

        return userInput;
    }

    @Override
    public void setValidador(Validador v) {
        this.promptService.setValidador(Objects.requireNonNull(v, this.getClass().getName() + ": validador nulo."));
    }
}