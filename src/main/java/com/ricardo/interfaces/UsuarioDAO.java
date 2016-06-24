package com.ricardo.interfaces;

import com.ricardo.pessoa.Usuario;

import java.util.List;

/**
 * Created by ricardo on 31/05/16.
 */
public interface UsuarioDAO {
    Usuario getUsuarioPorLogin(String login);

    List<Usuario> getUsuariosSistema();

    /**
     * Verifica se existe pelo menos um usuário admin cadastrado no sistema.
     *
     * @return True se existe pelo menos um admin cadastrado e false caso contrário.
     */
    boolean existeAdmin();

    boolean existeUsuario(String login);

    void inserirUsuario(Usuario usuario);
}
