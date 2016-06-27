package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.QuartoService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.interfaces.Tela;
import com.ricardo.promptdecorators.PromptComMensagemErro;
import com.ricardo.suites.Quarto;
import com.ricardo.suites.Reserva;
import com.ricardo.validacao.PreDefinedValidators;

import java.util.List;
import java.util.Objects;

/**
 * Created by ricardo on 27/05/16.
 * Classe responsável pela tela que exibe as reservas de um único quarto..
 */
public class ExibeReservasQuarto implements Tela {
    private final ReservaService reservaService;
    private final QuartoService quartoService;
    private PromptService promptService;

    public ExibeReservasQuarto(ReservaService reservaService, QuartoService quartoService, PromptService promptService) {
        this.reservaService = Objects.requireNonNull(reservaService, "ReservaService nulo no construtor de " + ExibeReservasQuarto.class.getName());
        this.quartoService = Objects.requireNonNull(quartoService, "QuartoService nulo no construtor de " + ExibeReservasQuarto.class.getName());
        this.promptService = Objects.requireNonNull(promptService, "PromptService nulo no construtor de " + ExibeReservasQuarto.class.getName());
    }

    /**
     * Inicia tela.
     */
    @Override
    public void iniciarTela() {
        promptService = new PromptComMensagemErro(promptService);
        promptService.setValidador(PreDefinedValidators.getValidadorQuarto());
        String numeroQuarto = promptService.getUserData("Número do quarto:");

        if (!quartoService.existeQuarto(new Quarto(numeroQuarto))) {
            System.out.println("O quarto " + numeroQuarto + " não está cadastrado no sistema.");
            return;
        }

        List<Reserva> reservas = reservaService.getReservasPorQuarto(new Quarto(numeroQuarto));

        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada para o quarto " + numeroQuarto);
        } else {
            for (Reserva reserva : reservas) {
                System.out.println("O quarto " + numeroQuarto + " possui as seguintes reservas:");
                System.out.println("--------------------------------------------------");
                System.out.println("Data de entrada: " + reserva.getDataInicial());
                System.out.println("Data de saída: " + reserva.getDataFinal());
                System.out.println("--------------------------------------------------");
            }
        }
    }
}
