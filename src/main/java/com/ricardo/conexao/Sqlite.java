package com.ricardo.conexao;

import com.ricardo.interfaces.BaseDeDados;
import com.ricardo.util.CloseQuietly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ricardo on 16/05/16.
 * Classe utilizada para abrir uma conexão com o Sqlite.
 */
public class Sqlite implements BaseDeDados {
    /**
     * Conecta com uma base de dados Sqlite.
     *
     * @return Connection em caso de sucesso.
     * @throws SQLException Caso a tentativa de conexão falhar.
     */
    @Override
    public Connection conectar() throws SQLException {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            // Conecta com a base de dados. Se não existir, cria.
            c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
        } catch (SQLException e) {
            throw e;
        } finally {
            return c;
        }
    }

    /**
     * Encerra a conexão c com a base de dados.
     * @param c Conexão a ser fechada.
     */
    @Override
    public void fecharConexao(Connection c) {
        CloseQuietly.close(c);
    }
}
