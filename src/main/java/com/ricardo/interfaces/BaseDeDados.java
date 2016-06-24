package com.ricardo.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ricardo on 25/05/16.
 */
public interface BaseDeDados {
    Connection conectar() throws SQLException;

    void fecharConexao(Connection c);
}
