package com.ricardo.util;

import javax.persistence.EntityManager;

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

    /**
     * Fecha o EntityManager.
     *
     * @param eM EntityManager a ser fechado.
     */
    public static void close(EntityManager eM) {
        if (eM == null)
            return;

        try {
            eM.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
