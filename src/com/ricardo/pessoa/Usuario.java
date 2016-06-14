package com.ricardo.pessoa;

import java.util.Objects;

/**
 * Created by ricardo on 01/06/16.
 * Classe responsável por definir o comportamento e estado pertinente a todos os usuários por padrão.
 */
public abstract class Usuario {
    private String nome;
    private String login;
    private String hashSenha;
    private boolean isAdmin;

    public Usuario() { }

    public Usuario(String login) {
        this.login = Objects.requireNonNull(login, this.getClass().getName() + ": login nulo.");
    }

    public Usuario(String nome, String login, String hashSenha, byte isAdmin) {
        this.nome = Objects.requireNonNull(nome, this.getClass().getName() + ": nome nulo.");
        this.login = Objects.requireNonNull(login, this.getClass().getName() + ": login nulo.");
        this.hashSenha = Objects.requireNonNull(hashSenha, this.getClass().getName() + ": hashSenha nulo.");
        Objects.requireNonNull(isAdmin, this.getClass().getName() + ": isAdmin nulo.");
        this.isAdmin = Byte.toString(isAdmin).equals("1"); // Não tem tipo boolean no dataaccess. Tem que fazer essa gambiarra.
    }

    /**
     * Retorna o nome do usuário.
     *
     * @return String contendo o nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define um nome para o usuário
     *
     * @param nome String contendo o nome do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o login do usuário.
     *
     * @return String contendo login do usuário.
     */
    public String getLogin() { return login; }

    /**
     * Define um login para o usuário.
     *
     * @param login String contendo o login do usuário.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retorna o hash da senha do usuário.
     *
     * @return String contendo o hash da senha do usuário.
     */
    public String getHashSenha() {
        return hashSenha;
    }

    /**
     * Define o hash da senha do usuário.
     *
     * @param hashSenha String contendo o hash da senha.
     */
    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }

    /**
     * Verifica se o usuário é administrador.
     *
     * @return true se usuário for administrador. false caso contrário.
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Define se o usuário é administrador ou não.
     *
     * @param admin true se usuário for administrador. false caso contrário.
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
