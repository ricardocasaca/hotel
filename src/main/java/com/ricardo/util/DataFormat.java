package com.ricardo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ricardo on 17/05/16.
 * Classe utilitária para conversão de formato de data.
 */
public class DataFormat {
    private static final Logger log = Logger.getLogger(DataFormat.class.getName());

    /**
     * Converte a string 'dd/MM/aaaa;HH:mm' para o formato Date.
     * [dd: Dia], [MM: Mês], [aaaa: Ano], [HH: Hora (0 - 23)], [mm: Minuto]
     * <p>
     * Exemplo: 11/10/1988;23:43
     *
     * @param data String no formato dd/MM/aaaa;HH:mm
     * @return Objeto Date com a data e a hora especificados.
     */
    public static Date strToDateTime(String data) {
        Date dataConvertida = null;

        try {
            dataConvertida = new SimpleDateFormat("dd/MM/yyyy;HH:mm").parse(data);
        } catch (ParseException e) {
            log.log(Level.SEVERE, e.toString(), e);
        }

        return dataConvertida;
    }

    /**
     * Converte a data para a String 'dd/MM/aaaa;HH:mm'.
     * [dd: Dia], [MM: Mês], [aaaa: Ano], [HH: Hora (0 - 23)], [mm: Minuto]
     * <p>
     * Exemplo: 11/10/1988;23:43
     *
     * @param data Objeto Date.
     * @return String no formato dd/MM/aaaa;HH:mm
     */
    public static String dateTimeToStr(Date data) {
        return new SimpleDateFormat("dd/MM/yyyy;HH:mm").format(data);
    }

    /**
     * Incrementa ou decrementa os dias de uma data.
     *
     * @param d          Objeto Date.
     * @param incremento Incremento desejado. Valores maiores do que zero incrementam a data.
     *                   Valores menores do que zero decrementam a data.
     * @return Objeto Date incrementado/decrementado.
     */
    public static Date dateAddBy(Date d, int incremento) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, incremento);
        return c.getTime();
    }
}
