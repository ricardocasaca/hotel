package com.ricardo.interfaces;

import com.ricardo.pessoa.Usuario;
import com.ricardo.suites.Quarto;
import com.ricardo.suites.Reserva;

import java.util.Date;
import java.util.List;

/**
 * Created by ricardo on 31/05/16.
 */
public interface ReservaService {
    List<Reserva> getReservasPorQuarto(Quarto q);

    List<Reserva> getReservasPorData(Date data);

    void cadastrarReserva(Reserva r);

    List<Reserva> getReservasPorUsuario(Usuario u);
}
