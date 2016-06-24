package com.ricardo.interfaces;

import com.ricardo.pessoa.Usuario;
import com.ricardo.suites.Quarto;
import com.ricardo.suites.Reserva;

import java.util.List;

/**
 * Created by ricardo on 31/05/16.
 */
public interface ReservaDAO {
    // Retorna todas as reservas do quarto.
    List<Reserva> getReservasPorQuarto(Quarto quarto);

    List<Reserva> getReservasPorData(String data);

    void inserirReserva(Reserva reserva);

    List<Reserva> getReservasPorUsuario(Usuario usuario);
}
