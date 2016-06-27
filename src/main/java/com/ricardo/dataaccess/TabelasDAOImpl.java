package com.ricardo.dataaccess;

import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.interfaces.TabelasDAO;
import com.ricardo.util.CloseQuietly;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * Created by ricardo on 16/05/16.
 * Classe responsável por gerar as tabelas na base de dados.
 */
public class TabelasDAOImpl implements TabelasDAO {
    private ConexaoHandler conexaoHandler;

    /**
     * Inicializa um objeto do tipo TabelaDAO.
     *
     * @param conexaoHandler ConexaoHandler utilizado para gerenciar as conexões.
     */
    public TabelasDAOImpl(ConexaoHandler conexaoHandler) {
        Objects.requireNonNull(conexaoHandler, "Parâmetro dataaccess para conexão está null.");
        this.conexaoHandler = conexaoHandler;
    }

    /**
     * Cria as tabelas necessários para o funcionamento da aplicação.
     *
     * @throws SQLException Caso algum erro ocorra.
     */
    @Override
    public void criarTabelas() throws SQLException {
        Statement stmt = null;
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            stmt = c.createStatement();

            String sql = "CREATE TABLE TblUsuario " +
                    "(login TEXT PRIMARY KEY NOT NULL," +
                    " nome TEXT NOT NULL," +
                    " hashSenha TEXT NOT NULL," +
                    " isAdmin INTEGER NOT NULL)";

            stmt.executeUpdate(sql);

            sql = "CREATE TABLE TblQuarto " +
                    "(numero TEXT PRIMARY KEY NOT NULL)";

            stmt.executeUpdate(sql);

            sql = "CREATE TABLE TblReserva " +
                    "(idReserva INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " dataInicial TEXT NOT NULL," +
                    " dataFinal TEXT NOT NULL," +
                    " horaInicial TEXT NOT NULL," +
                    " horaFinal TEXT NOT NULL," +
                    " numeroQuarto TEXT NOT NULL," +
                    " usuarioLogin TEXT NOT NULL," +
                    " FOREIGN KEY(numeroQuarto) REFERENCES TblQuarto(numero)," +
                    " FOREIGN KEY(usuarioLogin) REFERENCES TblUsuario(login))";

            stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw new SQLException("Erro ao construir tabelas");
        } finally {
            CloseQuietly.close(stmt);

            if (c != null)
                this.conexaoHandler.liberarConexao(c);
        }
    }
}
