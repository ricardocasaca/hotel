package com.ricardo.servicos;

import com.ricardo.dataaccess.UsuarioDAOImpl;
import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.interfaces.UsuarioDAO;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.pessoa.Usuario;

import java.util.List;
import java.util.Objects;

/**
 * Created by ricardo on 25/05/16.
 * Classe de serviço responsável por realizar operações referentes a usuário.
 */
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioDAO usuarioDAO;

    /**
     * Inicializa um serviço do tipo UsuarioService.
     *
     * @param conexaoHandler Referência para ConexaoHandler responsável por gerenciar conexões.
     */
    public UsuarioServiceImpl(ConexaoHandler conexaoHandler) {
        Objects.requireNonNull(conexaoHandler, "Parametro de conexão nulo em UsuarioServiceImpl");
        this.usuarioDAO = new UsuarioDAOImpl(conexaoHandler);
    }

    /**
     * Busca usuário por login.
     *
     * @param login String contendo o login do usuário a ser buscado.
     * @return Referência para usuário. Nulo caso não encontre.
     */
    @Override
    public Usuario getUsuarioPorLogin(String login) { return this.usuarioDAO.getUsuarioPorLogin(login); }

    /**
     * Busca todos os usuários do sistema.
     *
     * @return Lista contendo os usuários buscados. Ou lista vazia caso nenhum seja encontrado.
     */
    @Override
    public List<Usuario> getUsuariosSistema() {
        return this.usuarioDAO.getUsuariosSistema();
    }

    /**
     * Verifica a existência de um usuário.
     *
     * @param u Referência para o usuário a ser verificado.
     * @return true se usuário existe ou false caso contrário.
     */
    @Override
    public boolean existeUsuario(Usuario u) {
        return this.usuarioDAO.existeUsuario(u.getLogin());
    }

    /**
     * Verifica a existência de pelo menos um administrador no sistema.
     *
     * @return true se existe pelo menos um administrador cadastrado. false caso contrário.
     */
    @Override
    public boolean existeAdmin() {
        return this.usuarioDAO.existeAdmin();
    }

    /**
     * Cadastra um usuário no sistema.
     *
     * @param u Referência para usuário a ser cadastrado.
     */
    @Override
    public void cadastrarUsuario(Usuario u) {
        this.usuarioDAO.inserirUsuario(u);
    }
}
