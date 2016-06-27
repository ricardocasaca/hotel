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
    /**
     * Busca todos os quartos cadastrados no sistema.
     *
     * @return Lista com todos os quartos do sistema. Retorna
     * uma lista vazia caso nenhum seja encontrado.
     */
    @Override
    public List<Quarto> getAllQuartos() {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM Quarto c");
        return query.getResultList();
    }

    /**
     * Busca quarto por número.
     *
     * @param numero Número do quarto desejado.
     * @return Objeto Quarto se existir ou nulo caso contrário.
     */
    @Override
    public Quarto getQuartoPorNumero(String numero) {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        return entityManager.find(Quarto.class, numero);
    }

    /**
     * Cadastra o quarto indicado na base de dados.
     *
     * @param quarto Quarto a ser cadastrado.
     */
    @Override
    public void inserirQuarto(Quarto quarto) {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(quarto);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
