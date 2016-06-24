package com.ricardo.tela;

import com.ricardo.funcoesmenu.SairMenu;
import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.Tela;
import com.ricardo.menu.ConstruirMenu;
import com.ricardo.pessoa.Usuario;

/**
 * Created by ricardo on 27/05/16.
 * Classe responsável por montar e mostrar o menu de administrador.
 */
public class MenuAdmin extends Menu implements Tela {
    public MenuAdmin(Usuario usuario, PromptService p) {
        super(usuario, p);
    }

    /**
     * Inicia tela.
     */
    @Override
    public void iniciarTela() {
        boolean op;

        com.ricardo.interfaces.Menu menu = new ConstruirMenu()
                .addItemMenu("1", "Listar quartos cadastrados.", new com.ricardo.funcoesmenu.ExibeQuartos())
                .addItemMenu("2", "Cadastrar novo quarto.", new com.ricardo.funcoesmenu.CadastroQuarto())
                .addItemMenu("3", "Efetuar reserva.", new com.ricardo.funcoesmenu.CadastroReserva(super.usuario))
                .addItemMenu("4", "Consultar disponibilidade de quarto.", new com.ricardo.funcoesmenu.ExibeReservasQuarto())
                .addItemMenu("5", "Consultar reservas por data.", new com.ricardo.funcoesmenu.ExibeReservasData())
                .addItemMenu("6", "Listar usuários do sistema.", new com.ricardo.funcoesmenu.ExibeUsuariosSistema())
                .addItemMenu("7", "Verificar reservas por usuário.", new com.ricardo.funcoesmenu.ExibeReservasPorUsuario())
                .addItemMenu("8", "Cadastrar novo usuário.", new com.ricardo.funcoesmenu.CadastroUsuario())
                .addItemMenu("0", "Sair", new SairMenu())
                .construir();

        do {
            menu.mostrarMenu();
            String opcao = this.promptService.getUserData("\n\nOpção: ");
            op = menu.executarOpcao(opcao);
        } while (op);
    }
}
