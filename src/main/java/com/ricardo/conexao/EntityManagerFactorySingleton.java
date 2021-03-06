package com.ricardo.conexao;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by ricardo on 23/06/16.
 */
public class EntityManagerFactorySingleton {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("basepadrao");
    private static EntityManagerFactorySingleton ourInstance = new EntityManagerFactorySingleton();

    private EntityManagerFactorySingleton() {
    }

    public static EntityManagerFactorySingleton getInstance() {
        return ourInstance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
