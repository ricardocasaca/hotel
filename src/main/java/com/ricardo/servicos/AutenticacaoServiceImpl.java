package com.ricardo.servicos;

import com.ricardo.interfaces.AutenticacaoService;
import com.ricardo.interfaces.ConexaoHandler;
import com.ricardo.interfaces.UsuarioService;
import com.ricardo.pessoa.Usuario;

import java.util.Objects;

/**
 * Created by ricardo on 25/05/16.
 * Classe responsável por autenticar um usuário no sistema.
 */
public class AutenticacaoServiceImpl implements AutenticacaoService {
    private ConexaoHandler conexaoHandler;
    private UsuarioService usuarioService;

    /**
     * Inicializa um AutenticacaoService.
     *
     * @param conexaoHandler ConexaoHandler para gerenciar as conexões.
     */
    public AutenticacaoServiceImpl(ConexaoHandler conexaoHandler, UsuarioService uS) {
        this.conexaoHandler = Objects.requireNonNull(conexaoHandler, this.getClass().getName() + ": Parametro de conexao nulo.");
        this.usuarioService = Objects.requireNonNull(uS, this.getClass().getName() + ": UsuarioService nulo.");
    }

    /**
     * Tenta autenticar o usuário.
     *
     * @param u Usuário a ser autenticado.
     * @return true se for autenticado com sucesso. false caso contrário.
     */
    @Override
    public boolean autenticarUsuario(Usuario u) {
        // Se o usuário não existir no banco.
        if (!usuarioService.existeUsuario(u)) {
            return false;
        }

        Usuario usuario = usuarioService.getUsuarioPorLogin(u.getLogin()); // TODO: Isso aqui está horrível. Criar um método que verifica direto na base.
        return usuario.getHashSenha().equals(u.getHashSenha()); // Se o hash bater, autentica usuário, retorna true.
    }
}
