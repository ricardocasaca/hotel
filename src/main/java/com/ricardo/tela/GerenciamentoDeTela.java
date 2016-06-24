package com.ricardo.tela;

import com.ricardo.interfaces.Tela;

import java.util.Objects;

/**
 * Created by ricardo on 25/05/16.
 * Classe responsável pelo gerênciamento das telas.
 */
public class GerenciamentoDeTela {
    private Tela tela;

    public GerenciamentoDeTela() { }

    /**
     * Inicializa um GerenciamentoDeTela.
     *
     * @param tela Referência para a tela a ser inicializada.
     */
    public GerenciamentoDeTela(Tela tela) {
        this.tela = Objects.requireNonNull(tela, "Tela nula em GerenciamentoDeTela");
    }

    public void setTela(Tela tela) { this.tela = Objects.requireNonNull(tela); }

    /**
     * Inicia a tela.
     */
    public void exibirTela() {
        this.tela.iniciarTela();
    }
}
