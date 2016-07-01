package com.ricardo.servicos;

import com.ricardo.conexao.EntityManagerFactorySingleton;
import com.ricardo.dataaccess.QuartoDAOImpl;
import com.ricardo.interfaces.EntityManagerFactoryFacade;
import com.ricardo.interfaces.QuartoDAO;
import com.ricardo.interfaces.QuartoService;
import com.ricardo.suites.Quarto;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * @author Ricardo Freire Casaca
 *         Classe de serviço responsável por realizar operações referentes a um quarto.
 */
public class QuartoServiceImpl implements QuartoService {
    private EntityManagerFactoryFacade entityManagerFactoryFacade;

    public QuartoServiceImpl(EntityManagerFactoryFacade eMFF) {
        this.entityManagerFactoryFacade = eMFF;
    }

    /**
     * Busca todos os quartos cadastrados no sistema.
     *
     * @return Lista contendo os quartos encontrados. Ou lista vazia caso não encontrar nenhum quarto.
     */
    @Override
    public List<Quarto> getQuartos() {
        QuartoDAO qDAO = new QuartoDAOImpl(this.entityManagerFactoryFacade);
        return qDAO.getAllQuartos();
    }

    /**
     * Cadastra um quarto no sistema.
     *
     * @param quarto Referência para o quarto a ser cadastrado.
     */
    @Override
    public void cadastrarQuarto(Quarto quarto) {
        QuartoDAO qDAO = new QuartoDAOImpl(this.entityManagerFactoryFacade);
        qDAO.inserirQuarto(quarto);
    }

    /**
     * Verifica se existe um quarto específico no sistema.
     *
     * @param quarto Referência para o quarto a ser procurado.
     * @return true se o quarto indicado estiver cadastrado. false caso contrário.
     */
    @Override
    public boolean existeQuarto(Quarto quarto) {
        QuartoDAO qDAO = new QuartoDAOImpl(this.entityManagerFactoryFacade);
        return qDAO.getQuartoPorNumero(quarto.getNumero()) != null;
    }
}
