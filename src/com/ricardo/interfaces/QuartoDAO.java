package com.ricardo.interfaces;

import com.ricardo.suites.Quarto;

import java.util.List;

/**
 * Created by ricardo on 31/05/16.
 */
public interface QuartoDAO {
    List<Quarto> getAllQuartos();

    Quarto getQuartoPorNumero(String numero);

    void inserirQuarto(Quarto quarto);
}
