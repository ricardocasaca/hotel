package com.ricardo.interfaces;

import javax.persistence.EntityManager;

/**
 * Created by ricardo on 30/06/16.
 */
public interface EntityManagerFactoryFacade {
    public EntityManager createEntityManager();
}
