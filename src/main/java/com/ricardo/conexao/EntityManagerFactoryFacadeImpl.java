package com.ricardo.conexao;

import com.ricardo.interfaces.EntityManagerFactoryFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Objects;

/**
 * Created by ricardo on 30/06/16.
 */
public class EntityManagerFactoryFacadeImpl implements EntityManagerFactoryFacade {
    private EntityManagerFactory entityManagerFactory;

    public EntityManagerFactoryFacadeImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = Objects.requireNonNull(entityManagerFactory, this.getClass().getName() + ": Argumento nulo no construtor.");
    }

    @Override
    public EntityManager createEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }
}
