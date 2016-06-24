package com.ricardo.suites;

import com.ricardo.pessoa.Usuario;

import javax.persistence.*;

/**
 * Created by ricardo on 16/05/16.
 */
@Entity
public class Reserva {
    @Id
    @GeneratedValue
    private int id;
    private String dataInicial;
    private String dataFinal;
    @ManyToOne
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String horaEntrada;
    private String horaSaida;

    public Reserva() {
    }

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