package com.ricardo.dataaccess;

import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.interfaces.QuartoDAO;
import com.ricardo.interfaces.ReservaDAO;
import com.ricardo.suites.Quarto;
import com.ricardo.util.CloseQuietly;
import org.hibernate.Session;

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
 * @author Ricardo Casaca
 *         Implementação concreta de um Data Access Object para um quarto.
 *         Esta classe é responsável por buscar e inserir informações relacionadas
 *         a um quarto.
 */

public class QuartoDAOImpl implements QuartoDAO {
    private ConexaoHandler conexaoHandler;

    /**
     * Inicializa um QuartoDAO
     *
     * @param conexaoHandler ConexaoHandler utilizado para gerenciar a conexão.
     */
    public QuartoDAOImpl(ConexaoHandler conexaoHandler) {
        Objects.requireNonNull(conexaoHandler, "Conexão nula em QuartoDAOImpl.");
        this.conexaoHandler = conexaoHandler;
    }

    /**
     * Busca todos os quartos cadastrados no sistema.
     *
     * @return Lista com todos os quartos do sistema. Retorna
     * uma lista vazia caso nenhum seja encontrado.
     */
    @Override
    public List<Quarto> getAllQuartos() {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM TblQuarto c");
        return query.getResultList();

        /*
        PreparedStatement query = null;
        ResultSet rs = null;
        List<Quarto> quartos = new ArrayList<>();
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            c.setAutoCommit(false);

            query = c.prepareStatement("SELECT * FROM TblQuarto;");
            rs = query.executeQuery();

            while (rs.next()) {
                String numQuarto = rs.getString("numero");
                Quarto quarto = new Quarto(numQuarto);
                quartos.add(quarto);
            }

            return quartos;
        } catch (ConnectException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            CloseQuietly.close(query);
            CloseQuietly.close(rs);

            if (c != null)
                this.conexaoHandler.liberarConexao(c);
        }

        return quartos;
        */
    }

    /**
     * Busca quarto por número.
     *
     * @param numero Número do quarto desejado.
     * @return Objeto Quarto se existir ou nulo caso contrário.
     */
    @Override
    public Quarto getQuartoPorNumero(String numero) {
        PreparedStatement query = null;
        ResultSet rs = null;
        Quarto quarto = null;
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            c.setAutoCommit(false);

            query = c.prepareStatement("SELECT * FROM TblQuarto WHERE numero = ?;");
            query.setString(1, numero);
            rs = query.executeQuery();

            if (rs.next()) {
                ReservaDAO reservaDAO = new ReservaDAOImpl(this.conexaoHandler);
                quarto = new Quarto(numero);
                quarto.setReservas(reservaDAO.getReservasPorQuarto(quarto));
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

        return quarto;
    }

    /**
     * Cadastra o quarto indicado na base de dados.
     *
     * @param quarto Quarto a ser cadastrado.
     */
    @Override
    public void inserirQuarto(Quarto quarto) {
        PreparedStatement query = null;
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            c.setAutoCommit(false);

            query = c.prepareStatement("INSERT INTO TblQuarto (numero) VALUES (?);");
            query.setString(1, quarto.getNumero());
            query.execute();
            c.commit();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ConnectException e) {
            e.printStackTrace();
        } finally {
            CloseQuietly.close(query);

            if (c != null)
                this.conexaoHandler.liberarConexao(c);
        }
    }
}
