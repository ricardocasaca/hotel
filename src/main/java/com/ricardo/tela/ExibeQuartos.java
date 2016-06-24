package com.ricardo.tela;

import com.ricardo.interfaces.Tela;
import com.ricardo.suites.Quarto;

import java.util.List;

/**
 * Created by ricardo on 27/05/16.
 * Classe responsável pela tela de exibição de quartos..
 */
public class ExibeQuartos implements Tela {
    private final List<Quarto> quartos;

    public ExibeQuartos(List<Quarto> quartos) {
        this.quartos = quartos;
    }

    // TODO: Não sei como testar isso.
    @Override
    public void iniciarTela() {
        System.out.println("Quartos cadastrados:");

        for (Quarto q : quartos) {
            System.out.println(q.getNumero());
        }
    }
}
