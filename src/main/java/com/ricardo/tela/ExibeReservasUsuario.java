package com.ricardo.tela;

import com.ricardo.interfaces.ReservaService;
import com.ricardo.interfaces.Tela;
import com.ricardo.pessoa.Usuario;
import com.ricardo.suites.Reserva;

import java.util.List;
import java.util.Objects;

/**
 * Created by ricardo on 30/05/16.
 * Classe responsável pela tela que exibe as reservas para um usuário.
 */
public class ExibeReservasUsuario implements Tela {
    private final Usuario usuario;
    private final ReservaService reservaService;

    public ExibeReservasUsuario(Usuario u, ReservaService rS) {
        this.usuario = Objects.requireNonNull(u, "Usuario nulo no construtor de " + ExibeReservasUsuario.class.getName());
        this.reservaService = Objects.requireNonNull(rS, "ReservaService nulo no construtor de " + ExibeReservasUsuario.class.getName());
    }

    /**
     * Inicia tela.
     */
    @Override
    public void iniciarTela() {
        List<Reserva> reservas;
        reservas = reservaService.getReservasPorUsuario(usuario);

        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada para o usuário \"" + usuario.getLogin() + "\"");
        } else {
            System.out.println("Lista de reservas do usuário \"" + usuario.getLogin() + "\":\n");

            for (Reserva r : reservas) {
                System.out.println("---------------------------------------");
                System.out.println("Quarto: " + r.getQuarto().getNumero());
                System.out.println("Entrada: " + r.getDataInicial());
                System.out.println("Saída: " + r.getDataFinal());
                System.out.println("---------------------------------------");
            }
        }
    }
}
