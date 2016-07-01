package com.ricardo.conexao;

import com.ricardo.interfaces.BaseDeDados;
import com.ricardo.interfaces.ConexaoHandler;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

/**
 * Created by ricardo on 06/06/16.
 */
public class ConexaoHandlerImplTest {
    @Rule
    public ExpectedException excecao = ExpectedException.none();

    @Test
    public void verificaSeListaDeConexoesLivresEAlteradaAoLiberarUmaConexao() throws Exception {
        BaseDeDados mockedBD = mock(BaseDeDados.class);
        ConexaoHandler cH = new ConexaoHandlerImpl(mockedBD, 1);
        assertEquals(0, cH.quantidadeConexoesLivres());
        Connection c = cH.getConexao();
        cH.liberarConexao(c);
        assertEquals(1, cH.quantidadeConexoesLivres()); // Após liberar a conexão, a lista de livres deve conte-la.
        cH.fecharConexoes();
    }

    @Test
    public void verificaSeListaDeConexoesEmUsoEAlteradaAoConectarELiberarUmaConexao() throws Exception {
        BaseDeDados mockedBD = mock(BaseDeDados.class);
        ConexaoHandler cH = new ConexaoHandlerImpl(mockedBD, 1);
        assertEquals(0, cH.quantidadeConexoesEmUso());
        Connection c = cH.getConexao();
        assertEquals(1, cH.quantidadeConexoesEmUso()); // Deve conter uma conexão.
        cH.liberarConexao(c);
        assertEquals(0, cH.quantidadeConexoesEmUso());
        cH.fecharConexoes();
    }

    @Test
    public void verificaSeMetodoDeConectarDaBaseDeDadosEChamado() throws ConnectException {
        BaseDeDados bD = mock(Sqlite.class);
        ConexaoHandler cH = new ConexaoHandlerImpl(bD, 3);
        cH.getConexao();
        try {
            verify(bD).conectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ConstrutorComBaseDeDadosNulaException() {
        excecao.expect(NullPointerException.class);
        ConexaoHandler cH = new ConexaoHandlerImpl(null, 3);
    }

    @Test
    public void ConstrutorComLimiteDeConexaoIlegal() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O limite de conexões deve ser no mínimo 1.");
        ConexaoHandler cH = new ConexaoHandlerImpl(mock(Sqlite.class), 0);
    }

    @Test
    public void verificaSeChamaOMetodoParaFecharConexoesDaBaseDeDados() throws Exception {
        BaseDeDados mockedBD = mock(BaseDeDados.class);
        ConexaoHandler cH = new ConexaoHandlerImpl(mockedBD, 1);
        cH.getConexao();
        cH.fecharConexoes();
        verify(mockedBD, times(1)).fecharConexao(anyObject());
    }

    @Test
    public void verificaSeConexaoVaiParaListaDeLivreQuandoELiberada() throws Exception {
        BaseDeDados mockedBD = mock(BaseDeDados.class);
        ConexaoHandler cH = new ConexaoHandlerImpl(mockedBD, 1);
        Connection c = cH.getConexao();
        assertEquals(1, cH.quantidadeConexoesEmUso()); // Garante que realmente tem uma conexão em uso.
        cH.liberarConexao(c);
        assertEquals(1, cH.quantidadeConexoesLivres()); // Garante que a conexão ficou livre para uso.
    }
}