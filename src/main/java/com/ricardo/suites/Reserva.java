package com.ricardo.suites;

import com.ricardo.pessoa.Usuario;
import com.ricardo.util.DataFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ricardo on 16/05/16.
 */
@Entity
public class Reserva {
    @Id
    @GeneratedValue
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEntrada;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraSaida;
    @ManyToOne
    @JoinColumn(name = "quarto_id", nullable = false)
    private Quarto quarto;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Reserva() {
    }

    public Reserva(Date dataHoraEntrada, Date dataHoraSaida, Quarto quarto, Usuario usuario) {
        this.dataHoraEntrada = dataHoraEntrada;
        this.dataHoraSaida = dataHoraSaida;
        this.quarto = quarto;
        this.usuario = usuario;
    }

    public String getDataEntradaString() {
        String dataHoraEntrada = DataFormat.dateTimeToStr(this.dataHoraEntrada);
        return dataHoraEntrada.split(";")[0];
    }

    public String getDataSaidaString() {
        String dataHoraSaida = DataFormat.dateTimeToStr(this.dataHoraSaida);
        return dataHoraSaida.split(";")[0];
    }

    public Date getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(Date dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public Date getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(Date dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}