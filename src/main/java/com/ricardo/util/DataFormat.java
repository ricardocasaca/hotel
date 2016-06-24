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
     * Converte data do formato dd/mm/aaaa para o formato aaaa-mm-dd.
     *
     * @param data String contendo a data a ser formatada no formato dd/mm/aaaa.
     * @return String contendo a data no formato aaaa-mm-dd. Retorna null em caso de falha.
     */
    public static String dataStrToSqliteStr(String data) {
        String dataFormatoSqlite = null;
        SimpleDateFormat objData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat objDataSqlite = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dataAux = objData.parse(data);
            dataFormatoSqlite = objDataSqlite.format(dataAux);
        } catch (ParseException e) { e.printStackTrace(); }

        return dataFormatoSqlite;
    }

    /**
     * Converte data do formato aaaa-mm-dd para o formato dd/mm/aaaa.
     *
     * @param data String contendo a data a ser formatada no formato aaaa-mm-dd.
     * @return String contendo a data no formato dd/mm/aaaa. Retorna null em caso de falha.
     */
    public static String sqliteDataToNormal(String data) {
        String dataNormal = null;
        SimpleDateFormat objDataSqlite = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat objDataNormal = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dataAux = objDataSqlite.parse(data);
            dataNormal = objDataNormal.format(dataAux);
        } catch (ParseException e) { e.printStackTrace(); }

        return dataNormal;
    }
}
