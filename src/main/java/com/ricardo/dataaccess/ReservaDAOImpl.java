package com.ricardo.dataaccess;

import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.interfaces.QuartoDAO;
import com.ricardo.interfaces.ReservaDAO;
import com.ricardo.interfaces.UsuarioDAO;
import com.ricardo.pessoa.Usuario;
import com.ricardo.suites.Quarto;
import com.ricardo.suites.Reserva;
import com.ricardo.util.CloseQuietly;
import com.ricardo.util.DataFormat;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by ricardo on 23/05/16.
 * Implementação concreta de um Data Access Object para uma reserva.
 * Esta classe é responsável por buscar e inserir informações relacionadas
 * a uma reserva.
 */
public class ReservaDAOImpl implements ReservaDAO {
    private ConexaoHandler conexaoHandler;

    /**
     * Inicializa um ReservaDAO.
     *
     * @param conexaoHandler ConexaoHandler utilizado para gerenciar as conexões.
     */
    public ReservaDAOImpl(ConexaoHandler conexaoHandler) {
        Objects.requireNonNull(conexaoHandler, "Conexão nula em QuartoDAOImpl.");
        this.conexaoHandler = conexaoHandler;
    }

    /**
     * Retorna todas as reservas registradas para o quarto indicado.
     *
     * @param quarto Quarto o qual se quer as reservas.
     * @return Lista de objetos Reserva. Se nenhuma for encontrada
     * retorna lista vazia.
     */
    @Override
    public List<Reserva> getReservasPorQuarto(Quarto quarto) {
        PreparedStatement query = null;
        ResultSet rs = null;
        List<Reserva> reservas = new ArrayList<>();
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            c.setAutoCommit(false);

            query = c.prepareStatement("SELECT * FROM TblReserva WHERE numeroQuarto = ? ORDER BY dataInicial;");
            query.setString(1, quarto.getNumero());
            rs = query.executeQuery();

            while (rs.next()) {
                String dataInicial = DataFormat.sqliteDataToNormal(rs.getString("dataInicial"));
                String dataFinal = DataFormat.sqliteDataToNormal(rs.getString("dataFinal"));
                String horaEntrada = rs.getString("horaInicial");
                String horaSaida = rs.getString("horaFinal");
                UsuarioDAO usuarioDAO = new UsuarioDAOImpl(this.conexaoHandler);
                Usuario usuario = usuarioDAO.getUsuarioPorLogin(rs.getString("usuarioLogin"));
                Reserva reserva = new Reserva(dataInicial, dataFinal, horaEntrada, horaSaida, quarto, usuario);
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            CloseQuietly.close(query);
            CloseQuietly.close(rs);

            if (c != null)
                this.conexaoHandler.liberarConexao(c);
        }

        return reservas;
    }

    /**
     * Busca reserva efetuada na data especificada.
     *
     * @param data Data da reserva no formato dd/mm/aaaa.
     * @return Lista com as reservas encontradas. Se nenhuma reserva for encotrada
     * retorna uma lista vazia.
     */
    @Override
    public List<Reserva> getReservasPorData(String data) {
        PreparedStatement query = null;
        ResultSet rs = null;
        List<Reserva> reservas = new ArrayList<>();
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            c.setAutoCommit(false);

            query = c.prepareStatement("SELECT * FROM TblReserva WHERE ? BETWEEN dataInicial AND dataFinal ORDER BY numeroQuarto");
            query.setString(1, DataFormat.dataStrToSqliteStr(data));
            rs = query.executeQuery();

            while (rs.next()) {
                String dataInicial = DataFormat.sqliteDataToNormal(rs.getString("dataInicial"));
                String dataFinal = DataFormat.sqliteDataToNormal(rs.getString("dataFinal"));
                String horaEntrada = rs.getString("horaInicial");
                String horaSaida = rs.getString("horaFinal");
                UsuarioDAO usuarioDAO = new UsuarioDAOImpl(this.conexaoHandler);
                Usuario usuario = usuarioDAO.getUsuarioPorLogin(rs.getString("usuarioLogin"));
                QuartoDAO quartoDAO = new QuartoDAOImpl(this.conexaoHandler);
                Quarto quarto = quartoDAO.getQuartoPorNumero(rs.getString("numeroQuarto"));
                Reserva reserva = new Reserva(dataInicial, dataFinal, horaEntrada, horaSaida, quarto, usuario);
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            CloseQuietly.close(query);
            CloseQuietly.close(rs);

            if (c != null)
                this.conexaoHandler.liberarConexao(c);
        }

        return reservas;
    }

    /**
     * Cadastra uma nova reserva na base de dados.
     *
     * @param reserva Reserva a ser cadastrada.
     */
    @Override
    public void inserirReserva(Reserva reserva) {
        PreparedStatement query = null;
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            c.setAutoCommit(false);

            query = c.prepareStatement("INSERT INTO TblReserva (dataInicial, dataFinal, horaInicial, horaFinal, numeroQuarto, usuarioLogin) VALUES (?, ?, '08:00', '00:00', ?, ?)");
            query.setString(1, reserva.getDataInicial());
            query.setString(2, reserva.getDataFinal());
            query.setString(3, reserva.getQuarto().getNumero());
            query.setString(4, reserva.getUsuario().getLogin());

            query.execute();
            c.commit();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            CloseQuietly.close(query);

            if (c != null)
                this.conexaoHandler.liberarConexao(c);
        }
    }

    /**
     * Busca reserva por usuário.
     *
     * @param usuario Usuário para o qual será buscadas as reservas.
     * @return Lista com as reservas. Se nenhuma for encontrada retorna
     * uma lista vazia.
     */
    @Override
    public List<Reserva> getReservasPorUsuario(Usuario usuario) {
        PreparedStatement query = null;
        ResultSet rs = null;
        List<Reserva> reservas = new ArrayList<>();
        Connection c = null;

        try {
            c = this.conexaoHandler.getConexao();
            c.setAutoCommit(false);

            query = c.prepareStatement("SELECT * FROM TblReserva WHERE usuarioLogin = ?");
            query.setString(1, usuario.getLogin());
            rs = query.executeQuery();

            while (rs.next()) {
                String dataInicial = DataFormat.sqliteDataToNormal(rs.getString("dataInicial"));
                String dataFinal = DataFormat.sqliteDataToNormal(rs.getString("dataFinal"));
                String horaEntrada = rs.getString("horaInicial");
                String horaSaida = rs.getString("horaFinal");
                QuartoDAO quartoDAO = new QuartoDAOImpl(this.conexaoHandler);
                Quarto quarto = quartoDAO.getQuartoPorNumero(rs.getString("numeroQuarto"));
                Reserva reserva = new Reserva(dataInicial, dataFinal, horaEntrada, horaSaida, quarto, usuario);
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            CloseQuietly.close(query);
            CloseQuietly.close(rs);

            if (c != null)
                this.conexaoHandler.liberarConexao(c);
        }

        return reservas;
    }
}
