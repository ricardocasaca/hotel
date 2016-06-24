package com.ricardo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ricardo on 17/05/16.
 * Classe utilitária para ler input de usuário na linha de comando.
 */
public class UserInput {
    /**
     * Pega input de usuário na linha de comando.
     *
     * @return String contendo o input fornecido pelo usuário.
     */
    public String lerInputUsuario() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
