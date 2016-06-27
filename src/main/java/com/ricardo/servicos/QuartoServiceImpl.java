package com.ricardo.servicos;

import com.ricardo.dataaccess.QuartoDAOImpl;
import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.interfaces.QuartoDAO;
import com.ricardo.interfaces.QuartoService;
import com.ricardo.suites.Quarto;

import java.util.List;
import java.util.Objects;

/**
 * @author Ricardo Freire Casaca
 *         Classe de serviço responsável por realizar operações referentes a um quarto.
 */
public class QuartoServiceImpl implements QuartoService {
    private final QuartoDAO quartoDAO;

    public QuartoServiceImpl() {
        this.quartoDAO = new QuartoDAOImpl();
    }

    /**
     * Busca todos os quartos cadastrados no sistema.
     *
     * @return Lista contendo os quartos encontrados. Ou lista vazia caso não encontrar nenhum quarto.
     */
    @Override
    public List<Quarto> getQuartos() { return this.quartoDAO.getAllQuartos(); }

    /**
     * Cadastra um quarto no sistema.
     *
     * @param quarto Referência para o quarto a ser cadastrado.
     */
    @Override
    public void cadastrarQuarto(Quarto quarto) { this.quartoDAO.inserirQuarto(quarto); }

    /**
     * Verifica se existe um quarto específico no sistema.
     *
     * @param quarto Referência para o quarto a ser procurado.
     * @return true se o quarto indicado estiver cadastrado. false caso contrário.
     */
    @Override
    public boolean existeQuarto(Quarto quarto) {
        return this.quartoDAO.getQuartoPorNumero(quarto.getNumero()) != null;
    }
}
