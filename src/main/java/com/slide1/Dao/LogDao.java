package com.slide1.Dao;

import javax.persistence.EntityManager;
import com.slide1.Entity.Log;
import com.slide1.Services.XJPA;

public class LogDao {
    public void create(Log entity) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
}