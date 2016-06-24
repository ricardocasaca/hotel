package com.ricardo.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ricardo on 01/06/16.
 */
public class DataFormatTest {
    private final String ddMmAaaa = "12/11/2015";
    private final String aaaaMmDd = "2015-11-12";

    @Test
    public void dataStrToSqliteStr() throws Exception {
        String dataConvertida = DataFormat.dataStrToSqliteStr(this.ddMmAaaa);
        assertEquals(this.aaaaMmDd, dataConvertida);
    }

    @Test
    public void sqliteDataToNormal() throws Exception {
        String dataConvertida = DataFormat.sqliteDataToNormal(this.aaaaMmDd);
        assertEquals(this.ddMmAaaa, dataConvertida);
    }
}