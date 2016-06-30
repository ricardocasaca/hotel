package com.ricardo.suites;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricardo on 16/05/16.
 * Classe utilizada para representar um quarto.
 */
@Entity
public class Quarto {
    @Id
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
