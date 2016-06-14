package com.ricardo.tela;

import com.ricardo.interfaces.PromptService;
import com.ricardo.interfaces.QuartoService;
import com.ricardo.interfaces.Tela;
import com.ricardo.suites.Quarto;

import java.util.Objects;

/**
 * Created by ricardo on 27/05/16.
 * Classe responsável pela tela que efetua o cadastro de quarto.
 */
public class CadastroQuarto implements Tela {
    private QuartoService quartoService;
    private PromptService promptService;

    /**
     * Inicializa um CadastroQuarto.
     *
     * @param promptService Referência para o prompt que será utilizado para pegar o input do usuário.
     * @param quartoService Referência para o service utiizado para efetuar as operações referentes ao quarto.
     */
    public CadastroQuarto(PromptService promptService, QuartoService quartoService) {
        this.quartoService = Objects.requireNonNull(quartoService);
        this.promptService = Objects.requireNonNull(promptService);
    }

    /**
     * Inicia a tela.
     */
    @Override
    public void iniciarTela() {
        String numeroQuarto = promptService.getUserData("Número do quarto a ser cadastrado (intervalo de 0 a 9999): ");
        this.quartoService.cadastrarQuarto(new Quarto(numeroQuarto));

        System.out.println("Quarto cadastrado com sucesso!");
    }
}
