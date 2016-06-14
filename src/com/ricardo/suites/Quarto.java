package com.ricardo.suites;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricardo on 16/05/16.
 * Classe utilizada para representar um quarto.
 */
public class Quarto {
    private final String numero;
    private List<Reserva> reservas;

    /**
     * Inicializa um Quarto.
     *
     * @param numero String contendo o número do quarto.
     */
    public Quarto(String numero) {
        this.numero = numero;
        this.reservas = new ArrayList<>();
    }

    /**
     * Inicializa um Quarto.
     *
     * @param numero   String contendo o número do quarto.
     * @param reservas Lista de reservas para este quarto.
     */
    public Quarto(String numero, List<Reserva> reservas) {
        this.numero = numero;
        this.reservas = reservas;
    }

    /**
     * Retorna o número do quarto.
     *
     * @return String contendo o número do quarto.
     */
    public String getNumero() {
        return this.numero;
    }

    /**
     * Retorna todas as reservas para este quarto.
     *
     * @return Lista com as reservas para o quarto. Lista vazia caso não haja nenhuma.
     */
    public List<Reserva> getReservas() { return this.reservas; }

    /**
     * Define as reservas para este quarto.
     *
     * @param reservas Lista contendo as reservas.
     */
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
