package com.ricardo.suites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ricardo on 16/05/16.
 * Classe utilizada para representar um quarto.
 */
@Entity
public class Quarto {
    @Id
    @GeneratedValue
    private int id;

    private String numero;

    public Quarto() {
    }

    /**
     * Inicializa um Quarto.
     *
     * @param numero String contendo o número do quarto.
     */
    public Quarto(String numero) {
        this.numero = numero;
    }

    /**
     * Retorna o número do quarto.
     *
     * @return String contendo o número do quarto.
     */
    public String getNumero() {
        return this.numero;
    }
}
