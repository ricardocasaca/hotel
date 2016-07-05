package com.ricardo.dataaccess;

import com.ricardo.interfaces.EntityManagerFactoryFacade;
import com.ricardo.interfaces.QuartoDAO;
import com.ricardo.suites.Quarto;
import com.ricardo.util.CloseQuietly;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ricardo Casaca
 *         Implementação concreta de um Data Access Object para um quarto.
 *         Esta classe é responsável por buscar e inserir informações relacionadas
 *         a um quarto.
 */

public class QuartoDAOImpl implements QuartoDAO {
    private static final Logger log = Logger.getLogger(QuartoDAOImpl.class.getName());
    private EntityManagerFactoryFacade entityManagerFactoryFacade;

    public QuartoDAOImpl(EntityManagerFactoryFacade eMFF) {
        this.entityManagerFactoryFacade = eMFF;
    }

    /**
     * Busca todos os quartos cadastrados no sistema.
     *
     * @return Lista com todos os quartos do sistema. Retorna
     * uma lista vazia caso nenhum seja encontrado.
     */
    @Override
    public List<Quarto> getAllQuartos() {
        Query query;
        EntityManager eM = this.entityManagerFactoryFacade.createEntityManager();

        try {
            // TODO Utilizar query tipadas!!!
            query = eM.createQuery("SELECT c FROM Quarto c");
            return query.getResultList();
        } finally {
            CloseQuietly.close(eM);
        }
    }

    /**
     * Busca quarto por número.
     *
     * @param numero Número do quarto desejado.
     * @return Objeto Quarto se existir ou nulo caso contrário.
     */
    @Override
    public Quarto getQuartoPorNumero(String numero) {
        Quarto q = null;
        EntityManager eM = this.entityManagerFactoryFacade.createEntityManager();

        try {
            q = eM.find(Quarto.class, numero);
        } finally {
            CloseQuietly.close(eM);
        }

        return q;
    }

    /**
     * Cadastra o quarto indicado na base de dados.
     *
     * @param quarto Quarto a ser cadastrado.
     */
    @Override
    public void inserirQuarto(Quarto quarto) {
        EntityManager eM = this.entityManagerFactoryFacade.createEntityManager();

        try {
            eM.getTransaction().begin();
            eM.persist(quarto);
            eM.getTransaction().commit();
        } catch (EntityExistsException | TransactionRequiredException e) {
            log.log(Level.SEVERE, e.toString(), e);
        } finally {
            CloseQuietly.close(eM);
        }
    }
}
