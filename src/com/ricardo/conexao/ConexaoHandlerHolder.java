package com.ricardo.conexao;

import com.ricardo.interfaces.ConexaoHandler;

/**
 * Created by ricardo on 08/06/16.
 * Singleton responsável por gerenciar os pools de conexão.
 */
public class ConexaoHandlerHolder {
    private static final ConexaoHandlerHolder conexaoHandlerHolder = new ConexaoHandlerHolder();
    private static final ConexaoHandler conexaoHandler = new ConexaoHandlerImpl(new Sqlite(), 1);

    private ConexaoHandlerHolder() {}

    public static ConexaoHandlerHolder getInstance() {
        return conexaoHandlerHolder;
    }

    /**
     * Retorna uma instância do ConexãoHandler para o Sqlite.
     *
     * @return ConexaoHandler para o Sqlite.
     */
    public ConexaoHandler getSqliteConHandler() {
        return conexaoHandler;
    }
}
