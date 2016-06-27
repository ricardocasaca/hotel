package com.ricardo.servicos;

import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.dataaccess.ReservaDAOImpl;
import com.ricardo.interfaces.ReservaDAO;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.pessoa.Usuario;
import com.ricardo.suites.Quarto;
import com.ricardo.suites.Reserva;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by ricardo on 24/05/16.
 * Classe de serviço responsável por realizar operações referentes a reserva.
 */
public class ReservaServiceImpl implements ReservaService {
    private EntityManagerFactory entityManagerFactory;

    public ReservaServiceImpl() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory();
    }

    /**
     * Busca as reservas efetuadas para um dado quarto.
     *
     * @param q Referência para o quarto no qual se quer buscar as reservas.
     * @return Lista com as reservas ou lista vazia caso não houver nenhuma reserva.
     */
    @Override
    public List<Reserva> getReservasPorQuarto(Quarto q) {
        ReservaDAO rDAO = new ReservaDAOImpl(entityManagerFactory.createEntityManager());
        return rDAO.getReservasPorQuarto(q);
    }

    /**
     * Busca reservas por uma data específica.
     *
     * @param data String contendo a data no formato dd/mm/aaaa, para a qual se quer buscar as reservas.
     * @return Lista contendo as reservas para a data especificada ou lista vazia caso não haja nenhuma reserva.
     */
    @Override
    public List<Reserva> getReservasPorData(String data) {
        ReservaDAO rDAO = new ReservaDAOImpl(entityManagerFactory.createEntityManager());
        return rDAO.getReservasPorData(data);
    }

    /**
     * Cadastra uma nova reserva na base de dados.
     *
     * @param r Referência para a reserva a ser cadastrada.
     */
    @Override
    public void setReserva(Reserva r) {
        ReservaDAO rDAO = new ReservaDAOImpl(entityManagerFactory.createEntityManager());
        rDAO.inserirReserva(r);
    }

    /**
     * Busca todas as reservas para um usuário específico.
     *
     * @param u Referência para o usuário no qual se quer buscar as reservas.
     * @return Lista contendo as reservas ou lista vazia caso nenhuma for encontrada.
     */
    @Override
    public List<Reserva> getReservasPorUsuario(Usuario u) {
        ReservaDAO rDAO = new ReservaDAOImpl(entityManagerFactory.createEntityManager());
        return rDAO.getReservasPorUsuario(u);
    }
}
