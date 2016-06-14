package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.pessoa.Usuario;

import java.util.Objects;

/**
 * Created by ricardo on 27/05/16.
 * Classe que representa uma abstração de menu.
 */
abstract class Menu {
    public final Usuario usuario;
    public PromptService promptService;

    Menu(Usuario usuario, PromptService p) {
        this.usuario = Objects.requireNonNull(usuario, this.getClass().getName() + ": usuario nulo.");
        this.promptService = Objects.requireNonNull(p, this.getClass().getName() + ": promptService nulo.");
    }
}
