package com.ricardo.tela;

import com.ricardo.ValidacaoHandler.ConstruirValidador;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.QuartoService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.interfaces.Tela;
import com.ricardo.pessoa.Usuario;
import com.ricardo.promptdecorators.PromptComMensagemErro;
import com.ricardo.suites.Quarto;
import com.ricardo.suites.Reserva;
import com.ricardo.util.DataFormat;
import com.ricardo.validacao.PreDefinedValidators;
import com.ricardo.validacao.ValidacaoDataFormato;
import com.ricardo.validacao.ValidacaoDataFutura;

import java.util.Objects;

/**
 * Created by ricardo on 25/05/16.
 * Classe responsável pela tela que efetua o cadastro de reserva.
 */
public class CadastroReserva implements Tela {
    private final Usuario usuario;
    private final QuartoService quartoService;
    private final ReservaService reservaService;
    private PromptService promptService;

    public CadastroReserva(Usuario usuario, PromptService p, QuartoService qS, ReservaService rS) {
        this.usuario = Objects.requireNonNull(usuario, "Usuario nulo no construtor de CadastroReserva");
        this.promptService = Objects.requireNonNull(p, "PromptService nulo no construtor de CadastroReserva");
        this.quartoService = Objects.requireNonNull(qS, "QuartoService nulo no construtor de CadastroReserva");
        this.reservaService = Objects.requireNonNull(rS, "ReservaService nulo no construtor de CadastroReserva");
    }

    /**
     * Inicia a tela.
     */
    @Override
    public void iniciarTela() {
        Quarto quarto;
        promptService.setValidador(PreDefinedValidators.getValidadorQuarto());
        promptService = new PromptComMensagemErro(promptService);
        String numeroQuarto;
        numeroQuarto = promptService.getUserData("Entre com o número do quarto:");
        quarto = new Quarto(numeroQuarto);

        if (!quartoService.existeQuarto(quarto)) {
            System.out.println("Quarto não cadastrado!");
            return;
        } else {
            quarto.setReservas(reservaService.getReservasPorQuarto(quarto));
        }

        promptService.setValidador(new ConstruirValidador().addValidador(new ValidacaoDataFormato())
                .addValidador(new ValidacaoDataFutura())
                .construir());

        String dataEntrada = promptService.getUserData("Entre a data inicial da reserva no formato (dd/mm/aaaa).");
        String dataSaida = promptService.getUserData("Entre a data final da reserva no formato (dd/mm/aaaa).");

        Reserva reserva = new Reserva(DataFormat.strToDateTime(dataEntrada + ";14:00"), DataFormat.strToDateTime(dataSaida + ";12:00"), quarto, this.usuario);

        reservaService.setReserva(reserva);
        System.out.println("Reserva efetuada com sucesso!");
    }
}
