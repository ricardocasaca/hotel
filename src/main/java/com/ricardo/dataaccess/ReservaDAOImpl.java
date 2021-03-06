package com.ricardo.dataaccess;

import com.ricardo.interfaces.EntityManagerFactoryFacade;
import com.ricardo.interfaces.ReservaDAO;
import com.ricardo.pessoa.Usuario;
import com.ricardo.suites.Quarto;
import com.ricardo.suites.Reserva;
import com.ricardo.util.CloseQuietly;

import javax.persistence.*;
import javax.persistence.metamodel.Type;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ricardo on 23/05/16.
 * Implementação concreta de um Data Access Object para uma reserva.
 * Esta classe é responsável por buscar e inserir informações relacionadas
 * a uma reserva.
 */
public class ReservaDAOImpl implements ReservaDAO {
    private static final Logger log = Logger.getLogger(ReservaDAOImpl.class.getName());
    private EntityManagerFactoryFacade entityManagerFactoryFacade;

    public ReservaDAOImpl(EntityManagerFactoryFacade eMFF) {
        this.entityManagerFactoryFacade = Objects.requireNonNull(eMFF, this.getClass().getName() + ": Argumento nulo no construtor");
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
        TypedQuery<Reserva> query;
        EntityManager eM = this.entityManagerFactoryFacade.createEntityManager();

        try {
            query = eM.createQuery("SELECT c FROM Reserva c", Reserva.class);
            return query.getResultList();
        } finally {
            CloseQuietly.close(eM);
        }
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
        TypedQuery<Reserva> query;
        EntityManager eM = this.entityManagerFactoryFacade.createEntityManager();

        try {
            query = eM.createQuery("SELECT r FROM Reserva r WHERE :data BETWEEN r.dataHoraEntrada AND r.dataHoraSaida", Reserva.class);
            query.setParameter("data", data);
            return query.getResultList();
        } finally {
            CloseQuietly.close(eM);
        }
    }

    /**
     * Cadastra uma nova reserva na base de dados.
     *
     * @param reserva Reserva a ser cadastrada.
     */
    @Override
    public void inserirReserva(Reserva reserva) {
        EntityManager eM = this.entityManagerFactoryFacade.createEntityManager();

        try {
            eM.getTransaction().begin();
            eM.persist(reserva);
            eM.getTransaction().commit();
        } catch (EntityExistsException | TransactionRequiredException e) {
            log.log(Level.SEVERE, e.toString(), e);
        } finally {
            CloseQuietly.close(eM);
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
        TypedQuery<Reserva> query;
        EntityManager eM = this.entityManagerFactoryFacade.createEntityManager();
        try {
            query = eM.createQuery("SELECT r FROM Reserva r WHERE r.usuario = :usuario", Reserva.class);
            query.setParameter("usuario", usuario);
            return query.getResultList();
        } finally {
            CloseQuietly.close(eM);
        }
    }
}
