package com.ricardo.tela;

import com.ricardo.funcoesmenu.SairMenu;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.Tela;
import com.ricardo.menu.ConstruirMenu;
import com.ricardo.menu.MenuImpl;
import com.ricardo.pessoa.Usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ricardo on 27/05/16.
 * Classe responsável por montar e mostrar o menu de usuário.
 */
public class MenuUsuario extends Menu implements Tela {
    public MenuUsuario(Usuario usuario, PromptService p) { super(usuario, p); }

    /**
     * Inicia tela.
     */
    @Override
    public void iniciarTela() {
        boolean op;

        // Não tem mais o switch maldito :D
        com.ricardo.interfaces.Menu menu = new ConstruirMenu()
                .addItemMenu("1", "Consultar disponibilidade de quarto.", new com.ricardo.funcoesmenu.ExibeReservasQuarto())
                .addItemMenu("2", "Efetuar reserva.", new com.ricardo.funcoesmenu.CadastroReserva(super.usuario))
                .addItemMenu("3", "Minhas reservas.", new com.ricardo.funcoesmenu.ExibeReservasUsuario(super.usuario))
                .addItemMenu("0", "Sair", new SairMenu())
                .construir();

        do {
            menu.mostrarMenu();
            String opcao = this.promptService.getUserData("\n\nOpção: ");
            op = menu.executarOpcao(opcao);
        } while (op);
    }
}
