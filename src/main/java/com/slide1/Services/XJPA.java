package com.slide1.Services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class XJPA {
    static EntityManagerFactory emf;
    static EntityManager entityManager;
    static {
        // quản lí kết nối đến database
        emf = Persistence.createEntityManagerFactory("polyoe");
        entityManager = emf.createEntityManager();
    }
    public static EntityManager getEntityManager() {
        return entityManager;
    }

}
