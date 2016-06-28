package com.ricardo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ricardo on 17/05/16.
 * Classe utilitária para conversão de formato de data.
 */
public class DataFormat {
    /**
     * Converte a string 'dd/MM/aaaa;HH:mm' para o formato Date.
     * [dd: Dia], [MM: Mês], [aaaa: Ano], [HH: Hora (0 - 23)], [mm: Minuto]
     *
     * Exemplo: 11/10/1988;23:43
     * @param data String no formato dd/MM/aaaa;HH:mm
     * @return Objeto Date com a data e a hora especificados.
     */
    public static Date strToDateTime(String data) {
        Date dataConvertida = null;

        try {
            dataConvertida = new SimpleDateFormat("dd/MM/yyyy;HH:mm").parse(data);
        } catch (ParseException e) {
            // TODO: Colocar log dizendo que não deveria cair aqui nunca.
        }

        return dataConvertida;
    }

    /**
     * Converte a data para a String 'dd/MM/aaaa;HH:mm'.
     * [dd: Dia], [MM: Mês], [aaaa: Ano], [HH: Hora (0 - 23)], [mm: Minuto]
     *
     * Exemplo: 11/10/1988;23:43
     * @param data Objeto Date.
     * @return String no formato dd/MM/aaaa;HH:mm
     */
    public static String dateTimeToStr(Date data){
        return new SimpleDateFormat("dd/MM/yyyy;HH:mm").format(data);
    }
}
