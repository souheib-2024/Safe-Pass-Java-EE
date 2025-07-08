package com.safepass.dao;

import java.util.List;

import com.safepass.models.PasswordEntry;
import com.safepass.models.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PasswordEntryDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("safepassPU");

    public void save(PasswordEntry entry) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entry);
        em.getTransaction().commit();
        em.close();
    }

    public List<PasswordEntry> findByUser(User user) {
        EntityManager em = emf.createEntityManager();
        List<PasswordEntry> list = em.createQuery(
            "SELECT p FROM PasswordEntry p WHERE p.user = :user", PasswordEntry.class)
            .setParameter("user", user)
            .getResultList();
        em.close();
        return list;
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        PasswordEntry entry = em.find(PasswordEntry.class, id);
        if (entry != null) {
            em.getTransaction().begin();
            em.remove(entry);
            em.getTransaction().commit();
        }
        em.close();
    }
}
