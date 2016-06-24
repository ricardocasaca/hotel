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
    private ConexaoHandler conexaoHandler;

    /**
     * Inicializa um UsuarioDAO.
     *
     * @param conexaoHandler ConexaoHandler utilizado para gerenciar as conexões.
     */
    public UsuarioDAOImpl(ConexaoHandler conexaoHandler) {
        Objects.requireNonNull(conexaoHandler, "Conexão nula em QuartoDAOImpl.");
        this.conexaoHandler = conexaoHandler;
    }

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

        /*
        PreparedStatement query = null;
        ResultSet rs = null;
        Usuario usuario = null;
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            c.setAutoCommit(false);

            query = c.prepareStatement("SELECT * FROM TblUsuario WHERE (login = ?)");
            query.setString(1, login);
            rs = query.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioSimples(rs.getString("nome"), rs.getString("login"), rs.getString("hashSenha"),
                        rs.getByte("isAdmin"));
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            CloseQuietly.close(query);
            CloseQuietly.close(rs);

            if (c != null)
                this.conexaoHandler.liberarConexao(c);
        }

        return usuario;
        */
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

        /*
        PreparedStatement query = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            c.setAutoCommit(false);

            query = c.prepareStatement("SELECT * FROM TblUsuario");
            rs = query.executeQuery();

            while (rs.next()) {
                usuarios.add(new UsuarioSimples(rs.getString("nome"), rs.getString("login"), rs.getString("hashSenha"),
                        rs.getByte("isAdmin")));
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            CloseQuietly.close(query);
            CloseQuietly.close(rs);

            if (c != null)
                this.conexaoHandler.liberarConexao(c);
        }

        return usuarios;
        */
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
        return query.getFirstResult() > 0;

        /*
        PreparedStatement query = null;
        ResultSet rs = null;
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            c.setAutoCommit(false);

            query = c.prepareStatement("SELECT COUNT(*) AS total FROM TblUsuario WHERE (isAdmin = ?)");
            query.setInt(1, 1);
            rs = query.executeQuery();

            while (rs.next()) {
                // Se encontrar resultado é porque o usuário foi cadastrado.
                if (rs.getInt("total") > 0)
                    return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            CloseQuietly.close(query);
            CloseQuietly.close(rs);

            if (c != null)
                this.conexaoHandler.liberarConexao(c);
        }

        return false; // Usuário não cadastrado.
        */
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
        Query query = entityManager.createQuery("SELECT COUNT(*) FROM Usuario u WHERE u.logion = :login");
        query.setParameter("login", login);
        return query.getFirstResult() > 0;

        /*
        PreparedStatement query = null;
        ResultSet rs = null;
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            c.setAutoCommit(false);

            query = c.prepareStatement("SELECT COUNT(*) AS total FROM TblUsuario WHERE (login = ?)");
            query.setString(1, login);
            rs = query.executeQuery();

            while (rs.next()) {
                // Se encontrar resultado é porque o usuário foi cadastrado.
                if (rs.getInt("total") > 0)
                    return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            CloseQuietly.close(query);
            CloseQuietly.close(rs);

            if (c != null)
                this.conexaoHandler.liberarConexao(c);
        }

        return false; // Usuário não cadastrado.
        */
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

        /*
        PreparedStatement query = null;
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            c.setAutoCommit(false);

            query = c.prepareStatement(
                    "INSERT INTO TblUsuario (login, nome, hashSenha, isAdmin) VALUES (?, ?, ?, ?)");
            query.setString(1, usuario.getLogin());
            query.setString(2, usuario.getNome());
            query.setString(3, usuario.getHashSenha());
            query.setInt(4, (usuario.isAdmin()) ? 1 : 0);
            query.execute();
            c.commit();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            CloseQuietly.close(query);

            if (c != null)
                this.conexaoHandler.liberarConexao(c);
        }
        */
    }
}
