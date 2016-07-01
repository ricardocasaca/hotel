package com.ricardo.erro;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by ricardo on 10/06/16.
 */
public class PropertiesErroSingleton {
    private static final PropertiesErroSingleton pES = new PropertiesErroSingleton();
    private static final ResourceBundle rB = ResourceBundle.getBundle("msgerros", Locale.getDefault());

    private PropertiesErroSingleton() {
    }

    public static PropertiesErroSingleton getInstance() {
        return pES;
    }

    public ResourceBundle getProp() {
        return rB;
    }
}
