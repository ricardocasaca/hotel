package com.ricardo.pessoa;

/**
 * Created by ricardo on 16/05/16.
 * Classe responsável por representar um usuário simples no sistema.
 */
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
