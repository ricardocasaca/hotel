package com.ricardo.dataaccess;

import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.interfaces.QuartoDAO;
import com.ricardo.interfaces.ReservaDAO;
import com.ricardo.interfaces.UsuarioDAO;
import com.ricardo.pessoa.Usuario;
import com.ricardo.suites.Quarto;
import com.ricardo.suites.Reserva;
import com.ricardo.util.CloseQuietly;
import com.ricardo.util.DataFormat;

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
 * Created by ricardo on 23/05/16.
 * Implementação concreta de um Data Access Object para uma reserva.
 * Esta classe é responsável por buscar e inserir informações relacionadas
 * a uma reserva.
 */
public class ReservaDAOImpl implements ReservaDAO {
    /**
     * Retorna todas as reservas registradas para o quarto indicado.
     *
     * @param quarto Quarto o qual se quer as reservas.
     * @return Lista de objetos Reserva. Se nenhuma for encontrada
     * retorna lista vazia.
     */
    @Override
    public List<Reserva> getReservasPorQuarto(Quarto quarto) {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM Reserva c");
        return query.getResultList();
    }

    /**
     * Busca reserva efetuada na data especificada.
     *
     * @param data Data da reserva no formato dd/mm/aaaa.
     * @return Lista com as reservas encontradas. Se nenhuma reserva for encotrada
     * retorna uma lista vazia.
     */
    @Override
    public List<Reserva> getReservasPorData(String data) {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT r FROM Reserva r WHERE r.data BETWEEN dataInicial AND dataFinal ORDER BY r.numeroQuarto");
        return query.getResultList();
    }

    /**
     * Cadastra uma nova reserva na base de dados.
     *
     * @param reserva Reserva a ser cadastrada.
     */
    @Override
    public void inserirReserva(Reserva reserva) {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(reserva);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /**
     * Busca reserva por usuário.
     *
     * @param usuario Usuário para o qual será buscadas as reservas.
     * @return Lista com as reservas. Se nenhuma for encontrada retorna
     * uma lista vazia.
     */
    @Override
    public List<Reserva> getReservasPorUsuario(Usuario usuario) {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT r FROM Reserva r WHERE r.usuario = '" + usuario.getLogin() + "'");
        return query.getResultList();
    }
}
