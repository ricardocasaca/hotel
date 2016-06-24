package com.ricardo.tela;

import com.ricardo.interfaces.Tela;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.pessoa.Usuario;

import java.util.Objects;

/**
 * Created by ricardo on 27/05/16.
 * Classe responsável pela tela que exibe os usuários do sistema.
 */
public class ExibeUsuariosSistema implements Tela {
    private final UsuarioService usuarioService;

    public ExibeUsuariosSistema(UsuarioService usuarioService) {
        this.usuarioService = Objects.requireNonNull(usuarioService, "UsuarioService nulo no construtor de " + this.getClass().getName());
    }

    /**
     * Inicia tela.
     */
    @Override
    public void iniciarTela() {
        for (Usuario u : usuarioService.getUsuariosSistema()) {
            System.out.println(u.getLogin());
        }
    }
}
