package com.ricardo.servicos;

import com.ricardo.dataaccess.TabelasDAOImpl;
import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.interfaces.CriarTabelasService;
import com.ricardo.interfaces.TabelasDAO;

import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by ricardo on 06/06/16.
 * Classe de serviço responsável por criar as tabelas na base de dados.
 */
// TODO Classe não utilizada
public class CriarTabelasServiceImpl implements CriarTabelasService {
    private TabelasDAO tabelasDAO;

    /**
     * Inicializa um serviço CriarTabelas.
     *
     * @param conexaoHandler Referência para ConexaoHandler utilizado para gerenciar as conexões.
     */
    public CriarTabelasServiceImpl(ConexaoHandler conexaoHandler) {
        Objects.requireNonNull(conexaoHandler, "Parametro nulo em CriarTabelasImpl");
        this.tabelasDAO = new TabelasDAOImpl(conexaoHandler);
    }

    /**
     * Cria as tabelas na base de dados através do TabelasDAO.
     */
    @Override
    public void criarTabelas() {
        try {
            tabelasDAO.criarTabelas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
