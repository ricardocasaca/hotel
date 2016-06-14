package com.ricardo.conexao;

import com.ricardo.interfaces.BaseDeDados;
import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.util.CloseQuietly;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by ricardo on 06/06/16.
 * Implementação concreta de um ConexaoHandler. Esta classe é responsável
 * por gerenciar as conexões com o banco de dados especificado em seu construtor.
 * Por gerencia entenda como:
 * -Criar novas conexões quando necessário.
 * -Limitar o número de conexões que podem ser criadas em paralelo.
 * -Devolver uma conexão a um consumidor de forma transparente caso disponível.
 */
public class ConexaoHandlerImpl implements ConexaoHandler {
    private List<Connection> emUso = new ArrayList<>();
    private List<Connection> livres = new ArrayList<>();
    private BaseDeDados baseDeDados = null;
    private int limiteConexoes;

    /**
     * Configura o ConexaoHandler especificando a base de dados a ser utilizada e o máximo de conexões
     * permitidas em paralelo.
     *
     * @param baseDeDados    Instância da classe que representa o banco de dados que será utilizado.
     * @param limiteConexoes Quantidade máxima de conexões permitidas em paralelo.
     */
    public ConexaoHandlerImpl(BaseDeDados baseDeDados, int limiteConexoes) {
        this.baseDeDados = Objects.requireNonNull(baseDeDados, "Parametro nulo no construtor de ConexaoHandlerImpl");

        if (limiteConexoes >= 1) {
            this.limiteConexoes = limiteConexoes;
        } else {
            throw new IllegalArgumentException("O limite de conexões deve ser no mínimo 1.");
        }
    }

    /**
     * Retorna uma conexão pronta para uso.
     *
     * @return Connection
     * @throws ConnectException
     */
    @Override
    public Connection getConexao() throws ConnectException {
        if (!this.livres.isEmpty()) {
            return this.colocarConexaoEmUso();
        } else {
            if (this.criarConexao()) {
                return this.colocarConexaoEmUso();
            } else {
                throw new ConnectException("Não é possível criar uma nova conexão.");
            }
        }
    }

    private Connection colocarConexaoEmUso() {
        Connection c = this.livres.get(0);
        this.emUso.add(c);
        this.livres.remove(c);
        return c;
    }

    private boolean criarConexao() {
        int totalConexoes = this.emUso.size() + this.livres.size();

        if (totalConexoes < this.limiteConexoes) {
            try {
                this.livres.add(this.baseDeDados.conectar());
            } catch (SQLException e) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * Fecha todas as conexões criadas.
     */
    @Override
    public void fecharConexoes() {
        for (Connection c : this.emUso) {
            this.baseDeDados.fecharConexao(c);
        }

        for (Connection c : this.livres) {
            this.baseDeDados.fecharConexao(c);
        }
    }

    /**
     * Libera a conexão c para uso. Este método serve apenas para indicar que
     * a conexão especificada não está mais em uso e está livre para uso futuro.
     * Este método não fecha a conexão.
     *
     * @param c Conexão a ser liberada.
     */
    @Override
    public void liberarConexao(Connection c) {
        int index = this.emUso.indexOf(c);

        if (index != -1) {
            this.livres.add(c);
            this.emUso.remove(c);
        }
    }

    /**
     * Retorna a quantidade de conexões livres.
     * @return int indicando a quantidade de conexões livres.
     */
    @Override
    public int quantidadeConexoesLivres() {
        return this.livres.size();
    }

    /**
     * Retorna a quantidade de conexões em uso.
     * @return int indicando a quantidade de conexões em uso.
     */
    @Override
    public int quantidadeConexoesEmUso() {
        return this.emUso.size();
    }
}
