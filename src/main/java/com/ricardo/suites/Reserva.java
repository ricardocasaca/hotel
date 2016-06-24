package com.ricardo.suites;

import com.ricardo.pessoa.Usuario;

/**
 * Created by ricardo on 16/05/16.
 */
public class Reserva {
    private final String dataInicial;
    private final String dataFinal;
    private final Quarto quarto;
    private final Usuario usuario;
    private final String horaEntrada;
    private final String horaSaida;

    public Reserva(String dataInicial, String dataFinal, String horaEntrada, String horaSaida, Quarto quarto, Usuario usuario) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.quarto = quarto;
        this.usuario = usuario;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public Quarto getQuarto() { return quarto; }

    public Usuario getUsuario() { return usuario; }

    public String getHoraEntrada() { return horaEntrada; }

    public String getHoraSaida() { return horaSaida; }
}