package com.ricardo.servicos;

import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.dataaccess.UsuarioDAOImpl;
import com.ricardo.interfaces.UsuarioDAO;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.pessoa.Usuario;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by ricardo on 25/05/16.
 * Classe de serviço responsável por realizar operações referentes a usuário.
 */
public class UsuarioServiceImpl implements UsuarioService {
    private EntityManagerFactory entityManagerFactory;

    public UsuarioServiceImpl() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory();
    }

    /**
     * Busca usuário por login.
     *
     * @param login String contendo o login do usuário a ser buscado.
     * @return Referência para usuário. Nulo caso não encontre.
     */
    @Override
    public Usuario getUsuarioPorLogin(String login) {
        UsuarioDAO uDAO = new UsuarioDAOImpl(entityManagerFactory.createEntityManager());
        return uDAO.getUsuarioPorLogin(login);
    }

    /**
     * Busca todos os usuários do sistema.
     *
     * @return Lista contendo os usuários buscados. Ou lista vazia caso nenhum seja encontrado.
     */
    @Override
    public List<Usuario> getUsuariosSistema() {
        UsuarioDAO uDAO = new UsuarioDAOImpl(entityManagerFactory.createEntityManager());
        return uDAO.getUsuariosSistema();
    }

    /**
     * Verifica a existência de um usuário.
     *
     * @param u Referência para o usuário a ser verificado.
     * @return true se usuário existe ou false caso contrário.
     */
    @Override
    public boolean existeUsuario(Usuario u) {
        UsuarioDAO uDAO = new UsuarioDAOImpl(entityManagerFactory.createEntityManager());
        return uDAO.existeUsuario(u.getLogin());
    }

    /**
     * Verifica a existência de pelo menos um administrador no sistema.
     *
     * @return true se existe pelo menos um administrador cadastrado. false caso contrário.
     */
    @Override
    public boolean existeAdmin() {
        UsuarioDAO uDAO = new UsuarioDAOImpl(entityManagerFactory.createEntityManager());
        return uDAO.existeAdmin();
    }

    /**
     * Cadastra um usuário no sistema.
     *
     * @param u Referência para usuário a ser cadastrado.
     */
    @Override
    public void cadastrarUsuario(Usuario u) {
        UsuarioDAO uDAO = new UsuarioDAOImpl(entityManagerFactory.createEntityManager());
        uDAO.inserirUsuario(u);
    }
}
