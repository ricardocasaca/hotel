package com.ricardo.pessoa;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ricardo on 16/05/16.
 * Classe responsável por representar um usuário simples no sistema.
 */
@Entity
public class UsuarioSimples extends Usuario {
    public UsuarioSimples() {
        super();
    }

    public UsuarioSimples(String login) {
        super(login);
    }

    public UsuarioSimples(String nome, String login, String hashSenha, byte isAdmin) {
        super(nome, login, hashSenha, isAdmin);
    }
}
