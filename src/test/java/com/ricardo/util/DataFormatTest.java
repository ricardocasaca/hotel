package com.ricardo.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by ricardo on 01/06/16.
 */
public class DataFormatTest {
    @Test
    public void strToDateTime() throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy;HH:mm");
        Date data = parser.parse("20/07/2016;23:50");
        Date dataFromStr = DataFormat.strToDateTime("20/07/2016;23:50");

        assertEquals(data, dataFromStr);
    }

    @Test
    public void dateTimeToStr() throws ParseException {
        String strDataTeste = "11/10/1988;23:25";
        Date data = DataFormat.strToDateTime("11/10/1988;23:25");

        assertEquals(strDataTeste, DataFormat.dateTimeToStr(data));
    }
}