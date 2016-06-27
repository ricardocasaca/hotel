package com.ricardo.dataaccess;

import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.interfaces.QuartoDAO;
import com.ricardo.suites.Quarto;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import java.util.List;

/**
 * @author Ricardo Casaca
 *         Implementação concreta de um Data Access Object para um quarto.
 *         Esta classe é responsável por buscar e inserir informações relacionadas
 *         a um quarto.
 */

public class QuartoDAOImpl implements QuartoDAO {
    private EntityManager entityManager;

    public QuartoDAOImpl(EntityManager eM) {
        this.entityManager = eM;
    }

    /**
     * Busca todos os quartos cadastrados no sistema.
     *
     * @return Lista com todos os quartos do sistema. Retorna
     * uma lista vazia caso nenhum seja encontrado.
     */
    @Override
    public List<Quarto> getAllQuartos() {
        Query query = null;

        try {
            query = entityManager.createQuery("SELECT c FROM Quarto c");
        }catch (IllegalArgumentException e){

        }

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
        Quarto q = null;

        try {
            q = entityManager.find(Quarto.class, numero);
        }catch (IllegalArgumentException e){

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
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(quarto);
            entityManager.getTransaction().commit();
        }catch (EntityExistsException e){

        }catch (IllegalArgumentException e){

        }catch (TransactionRequiredException e){

        }
    }
}
