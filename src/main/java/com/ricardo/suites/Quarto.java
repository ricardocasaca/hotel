package com.ricardo.suites;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ricardo on 16/05/16.
 * Classe utilizada para representar um quarto.
 */
@Entity
public class Quarto {
    // TODO Não use isso como chave, utilize outro, pois se eu quiser desativar esse, e criar outro no lugar com o mesmo numeor, e agora josé?
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
