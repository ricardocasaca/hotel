package com.ricardo.util;

/**
 * Created by ricardo on 23/05/16.
 * Classe utilitária utilizada para fechar uma stream.
 */
public class CloseQuietly {
    /**
     * Fecha a stream.
     *
     * @param c Stream a ser fechada.
     */
    public static void close(AutoCloseable c) {
        if (c == null)
            return;

        try {
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
