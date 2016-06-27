package com.ricardo.dataaccess;

import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.interfaces.UsuarioDAO;
import com.ricardo.pessoa.Usuario;
import com.ricardo.pessoa.UsuarioSimples;
import com.ricardo.suites.Quarto;
import com.ricardo.util.CloseQuietly;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by ricardo on 24/05/16.
 * Implementação concreta de um Data Access Object para um usuário.
 * Esta classe é responsável por buscar e inserir informações relacionadas
 * a um usuário.
 */
public class UsuarioDAOImpl implements UsuarioDAO {
    /**
     * Busca usuário pelo login.
     *
     * @param login String indicando o login do usuário a ser buscado.
     * @return Usuario caso seja encontrado ou nulo caso contrário.
     */
    @Override
    public Usuario getUsuarioPorLogin(String login) {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        return entityManager.find(Usuario.class, login);
    }

    /**
     * Busca todos os usuários do sistema.
     *
     * @return Lista com os usuários encontrados ou lista vazia caso nenhum seja encontrado.
     */
    @Override
    public List<Usuario> getUsuariosSistema() {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT u FROM Usuario u");
        return query.getResultList();
    }

    /**
     * Varifica se existe pelo menos um usuário admin cadastrado.
     *
     * @return True se existe pelo menos um admin cadastrado. False caso contrário.
     */
    @Override
    public boolean existeAdmin() {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT COUNT(*) FROM Usuario u WHERE u.isAdmin = 1");

        return query.getResultList().size() > 0;
    }

    /**
     * Verifica se existe usuário com login especificado cadastrado no sistema.
     *
     * @param login String indicando o login do usuário a ser procurado.
     * @return True se for encontrado o usuário especificado ou false caso contrário.
     */
    @Override
    public boolean existeUsuario(String login) {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT COUNT(*) FROM Usuario u WHERE u.login = :login");
        query.setParameter("login", login);
        return query.getResultList().size() > 0;
    }

    /**
     * Cadastra um usuário no sistema.
     *
     * @param usuario Usuario a ser cadastrado no sistema.
     */
    @Override
    public void inserirUsuario(Usuario usuario) {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
