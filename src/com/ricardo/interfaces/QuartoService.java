package com.ricardo.interfaces;

import com.ricardo.suites.Quarto;

import java.util.List;

/**
 * @author Kennedy Oliveira
 */
public interface QuartoService {
    List<Quarto> getQuartos();

    void cadastrarQuarto(Quarto quarto);

    boolean existeQuarto(Quarto quarto);
}
