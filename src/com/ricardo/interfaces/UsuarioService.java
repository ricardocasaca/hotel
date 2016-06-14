package com.ricardo.interfaces;

import com.ricardo.pessoa.Usuario;

import java.util.List;

/**
 * Created by ricardo on 31/05/16.
 */
public interface UsuarioService {
    Usuario getUsuarioPorLogin(String login);

    List<Usuario> getUsuariosSistema();

    boolean existeUsuario(Usuario u);

    boolean existeAdmin();

    void cadastrarUsuario(Usuario u);
}
