package com.ricardo.tela;

import com.ricardo.ValidacaoHandler.ConstruirValidador;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.interfaces.Tela;
import com.ricardo.promptdecorators.PromptComMensagemErro;
import com.ricardo.suites.Reserva;
import com.ricardo.validacao.ValidacaoDataFormato;

import java.util.List;
import java.util.Objects;

/**
 * Created by ricardo on 27/05/16.
 * Classe responsável pela tela de exibição de reservas por data.
 */
public class ExibeReservasData implements Tela {
    private PromptService promptService;
    private ReservaService reservaService;

    public ExibeReservasData(PromptService promptService, ReservaService reservaService) {
        this.promptService = Objects.requireNonNull(promptService, "PromptService nulo no construtor de " + ExibeReservasData.class.getName());
        this.reservaService = Objects.requireNonNull(reservaService, "ReservaService nulo no construtor de " + ExibeReservasData.class.getName());
        ;
    }

    @Override
    public void iniciarTela() {
        promptService = new PromptComMensagemErro(promptService);
        promptService.setValidador(new ConstruirValidador().addValidador(new ValidacaoDataFormato()).construir());
        String data = promptService.getUserData("Data no formato dd/mm/aaaa: ");
        List<Reserva> reservas = reservaService.getReservasPorData(data);

        if (reservas.isEmpty()) {
            System.out.println("Nenhum quarto reservado para a data " + data);
        } else {
            System.out.println("Quarto/s reservados para esta data:");

            for (Reserva reserva : reservas) {
                System.out.println("--------------------------------------------------");
                System.out.println(reserva.getQuarto().getNumero());
                System.out.println("--------------------------------------------------");
            }
        }
    }
}
