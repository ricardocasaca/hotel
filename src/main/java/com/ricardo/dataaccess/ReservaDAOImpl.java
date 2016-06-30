package com.ricardo.dataaccess;

import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.interfaces.ReservaDAO;
import com.ricardo.pessoa.Usuario;
import com.ricardo.suites.Quarto;
import com.ricardo.suites.Reserva;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by ricardo on 23/05/16.
 * Implementação concreta de um Data Access Object para uma reserva.
 * Esta classe é responsável por buscar e inserir informações relacionadas
 * a uma reserva.
 */
public class ReservaDAOImpl implements ReservaDAO {
    private EntityManager entityManager;

    public ReservaDAOImpl(EntityManager eM) {
        this.entityManager = Objects.requireNonNull(eM, this.getClass().getName() + ": Argumento EntityManager nulo no construtor");
    }

    /**
     * Retorna todas as reservas registradas para o quarto indicado.
     *
     * @param quarto Quarto o qual se quer as reservas.
     * @return Lista de objetos Reserva. Se nenhuma for encontrada
     * retorna lista vazia.
     */
    @Override
    public List<Reserva> getReservasPorQuarto(Quarto quarto) {
        Query query = null;

        try {
            query = entityManager.createQuery("SELECT c FROM Reserva c");
        }catch (IllegalArgumentException e){

        }

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
    public List<Reserva> getReservasPorData(Date data) {
        Query query = null;

        try {
            query = entityManager.createQuery("SELECT r FROM Reserva r WHERE :data BETWEEN r.dataHoraEntrada AND r.dataHoraSaida");
            query.setParameter("data", data);
        }catch (IllegalArgumentException e){

        }

        return query.getResultList();
    }

    /**
     * Cadastra uma nova reserva na base de dados.
     *
     * @param reserva Reserva a ser cadastrada.
     */
    @Override
    public void inserirReserva(Reserva reserva) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(reserva);
            entityManager.getTransaction().commit();
        }catch (EntityExistsException e){

        }catch (IllegalArgumentException e){

        }catch (TransactionRequiredException e){

        }
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
        Query query = null;

        try {
            query = entityManager.createQuery("SELECT r FROM Reserva r WHERE r.usuario = '" + usuario.getLogin() + "'");
        }catch (IllegalArgumentException e){

        }

        return query.getResultList();
    }
}
