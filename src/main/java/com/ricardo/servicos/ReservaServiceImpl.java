package com.ricardo.servicos;

import com.ricardo.dataaccess.ReservaDAOImpl;
import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.interfaces.ReservaDAO;
import com.ricardo.interfaces.ReservaService;
import com.ricardo.pessoa.Usuario;
import com.ricardo.suites.Quarto;
import com.ricardo.suites.Reserva;

import java.util.List;
import java.util.Objects;

/**
 * Created by ricardo on 24/05/16.
 * Classe de serviço responsável por realizar operações referentes a reserva.
 */
public class ReservaServiceImpl implements ReservaService {
    private final ReservaDAO reservaDAO;

    /**
     * Inicializa um serviço ReservaService.
     *
     * @param conexaoHandler Referência para ConexaoHandler responsável por gerenciar as conexões.
     */
    public ReservaServiceImpl(ConexaoHandler conexaoHandler) {
        Objects.requireNonNull(conexaoHandler, "Parametro de conexão nulo em ReservaServiceImpl");
        this.reservaDAO = new ReservaDAOImpl(conexaoHandler);
    }

    /**
     * Busca as reservas efetuadas para um dado quarto.
     *
     * @param q Referência para o quarto no qual se quer buscar as reservas.
     * @return Lista com as reservas ou lista vazia caso não houver nenhuma reserva.
     */
    @Override
    public List<Reserva> getReservasPorQuarto(Quarto q) {
        return this.reservaDAO.getReservasPorQuarto(q);
    }

    /**
     * Busca reservas por uma data específica.
     *
     * @param data String contendo a data no formato dd/mm/aaaa, para a qual se quer buscar as reservas.
     * @return Lista contendo as reservas para a data especificada ou lista vazia caso não haja nenhuma reserva.
     */
    @Override
    public List<Reserva> getReservasPorData(String data) {
        return this.reservaDAO.getReservasPorData(data);
    }

    /**
     * Cadastra uma nova reserva na base de dados.
     *
     * @param r Referência para a reserva a ser cadastrada.
     */
    @Override
    public void setReserva(Reserva r) { this.reservaDAO.inserirReserva(r); }

    /**
     * Busca todas as reservas para um usuário específico.
     *
     * @param u Referência para o usuário no qual se quer buscar as reservas.
     * @return Lista contendo as reservas ou lista vazia caso nenhuma for encontrada.
     */
    @Override
    public List<Reserva> getReservasPorUsuario(Usuario u) { return this.reservaDAO.getReservasPorUsuario(u); }
}
