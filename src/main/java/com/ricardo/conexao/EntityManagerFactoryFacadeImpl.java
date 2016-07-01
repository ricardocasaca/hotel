package com.ricardo.conexao;

import com.ricardo.interfaces.EntityManagerFactoryFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by ricardo on 30/06/16.
 */
public class EntityManagerFactoryFacadeImpl implements EntityManagerFactoryFacade {
    private EntityManagerFactory entityManagerFactory;

    public EntityManagerFactoryFacadeImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public EntityManager createEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }
}