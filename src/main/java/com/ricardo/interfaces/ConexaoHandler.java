package com.ricardo.interfaces;

import java.net.ConnectException;
import java.sql.Connection;

/**
 * Created by ricardo on 07/06/16.
 */
public interface ConexaoHandler {
    Connection getConexao() throws ConnectException;

    void fecharConexoes();

    void liberarConexao(Connection c);

    int quantidadeConexoesLivres();

    int quantidadeConexoesEmUso();
}
