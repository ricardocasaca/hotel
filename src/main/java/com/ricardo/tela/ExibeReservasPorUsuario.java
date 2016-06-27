package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.interfaces.Tela;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.pessoa.Usuario;
import com.ricardo.pessoa.UsuarioSimples;
import com.ricardo.promptdecorators.PromptComMensagemErro;
import com.ricardo.suites.Reserva;
import com.ricardo.validacao.PreDefinedValidators;

import java.util.List;
import java.util.Objects;

/**
 * Created by ricardo on 27/05/16.
 * Classe responsável pela tela responsável por exibir reservas por usuário.
 */
public class ExibeReservasPorUsuario implements Tela {
    private final UsuarioService usuarioService;
    private final ReservaService reservaService;
    private PromptService promptService;

    public ExibeReservasPorUsuario(UsuarioService usuarioService, ReservaService reservaService, PromptService promptService) {
        this.usuarioService = Objects.requireNonNull(usuarioService, "UsuarioService nulo no construtor de ExibeReservasPorUsuario");
        this.reservaService = Objects.requireNonNull(reservaService, "ReservaService nulo no construtor de ExibeReservasPorUsuario");
        this.promptService = Objects.requireNonNull(promptService, "PromptService nulo no construtor de ExibeReservasPorUsuario");
    }

    @Override
    public void iniciarTela() {
        promptService = new PromptComMensagemErro(promptService);
        promptService.setValidador(PreDefinedValidators.getValidadorLogin());
        Usuario usuario = new UsuarioSimples(promptService.getUserData("Informe o usuário: "));

        while (!usuarioService.existeUsuario(usuario)) {
            System.out.println("Usuário \"" + usuario.getLogin() + "\" não cadastrado no sistema.");
            usuario.setLogin(promptService.getUserData("Informe outro usuário: "));
        }

        List<Reserva> reservas;
        reservas = reservaService.getReservasPorUsuario(usuario);

        // TODO Código duplicado com o ExibeReservasUsuario
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
