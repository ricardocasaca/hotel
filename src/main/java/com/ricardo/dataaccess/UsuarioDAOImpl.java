package com.ricardo.dataaccess;

import com.ricardo.interfaces.EntityManagerFactoryFacade;
import com.ricardo.interfaces.UsuarioDAO;
import com.ricardo.pessoa.Usuario;
import com.ricardo.util.CloseQuietly;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by ricardo on 24/05/16.
 * Implementação concreta de um Data Access Object para um usuário.
 * Esta classe é responsável por buscar e inserir informações relacionadas
 * a um usuário.
 */
public class UsuarioDAOImpl implements UsuarioDAO {
    private EntityManagerFactoryFacade entityManagerFactoryFacade;

    public UsuarioDAOImpl(EntityManagerFactoryFacade eMFF) {
        this.entityManagerFactoryFacade = Objects.requireNonNull(eMFF, this.getClass().getName() + ": Argumento nulo no construtor");
    }

    /**
     * Busca usuário pelo login.
     *
     * @param login String indicando o login do usuário a ser buscado.
     * @return Usuario caso seja encontrado ou nulo caso contrário.
     */
    @Override
    public Usuario getUsuarioPorLogin(String login) {
        Usuario u = null;
        EntityManager eM = this.entityManagerFactoryFacade.createEntityManager();

        try {
            u = eM.find(Usuario.class, login);
        } finally {
            CloseQuietly.close(eM);
        }

        return u;
    }

    /**
     * Busca todos os usuários do sistema.
     *
     * @return Lista com os usuários encontrados ou lista vazia caso nenhum seja encontrado.
     */
    @Override
    public List<Usuario> getUsuariosSistema() {
        Query query = null;
        EntityManager eM = this.entityManagerFactoryFacade.createEntityManager();

        try {
            query = eM.createQuery("SELECT u FROM Usuario u");
        } finally {
            CloseQuietly.close(eM);
        }

        return query.getResultList();
    }

    /**
     * Varifica se existe pelo menos um usuário admin cadastrado.
     *
     * @return True se existe pelo menos um admin cadastrado. False caso contrário.
     */
    @Override
    public boolean existeAdmin() {
        TypedQuery<Long> query;
        EntityManager eM = this.entityManagerFactoryFacade.createEntityManager();

        try {
            query = eM.createQuery("SELECT COUNT(*) FROM Usuario u WHERE u.isAdmin = 1", Long.class);
            return query.getSingleResult() > 0;
        } finally {
            CloseQuietly.close(eM);
        }
    }

    /**
     * Verifica se existe usuário com login especificado cadastrado no sistema.
     *
     * @param login String indicando o login do usuário a ser procurado.
     * @return True se for encontrado o usuário especificado ou false caso contrário.
     */
    @Override
    public boolean existeUsuario(String login) {
        TypedQuery<Long> query;
        EntityManager eM = this.entityManagerFactoryFacade.createEntityManager();

        try {
            query = eM.createQuery("SELECT COUNT(*) FROM Usuario u WHERE u.login = :login", Long.class);
            query = query.setParameter("login", login);
            return query.getSingleResult() > 0;
        } finally {
            CloseQuietly.close(eM);
        }
    }

    /**
     * Cadastra um usuário no sistema.
     *
     * @param usuario Usuario a ser cadastrado no sistema.
     */
    @Override
    public void inserirUsuario(Usuario usuario) {
        EntityManager eM = this.entityManagerFactoryFacade.createEntityManager();

        try {
            eM.getTransaction().begin();
            eM.persist(usuario);
            eM.getTransaction().commit();
        } catch (EntityExistsException e) {

        } catch (IllegalArgumentException e) {

        } catch (TransactionRequiredException e) {

        }finally {
            CloseQuietly.close(eM);
        }
    }
}
