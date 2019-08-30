package com.luigicardarella.application;

import com.luigicardarella.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class SimpleMainHibernateCRUD {

    private void run() throws IOException {

        EntityManager entityManager = Persistence
                .createEntityManagerFactory("user-unit")
                .createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        /* Persist a User entity */
        entityTransaction.begin();
        User user = new User("Luigi", "Luigi@domain.com");
        entityManager.persist(user);
        entityTransaction.commit();

        /* Fetch a User entity */
        entityManager.find(User.class, 1);

        /* Update a User entity */
        entityTransaction.begin();

        user = entityManager.find(User.class, 1);
        user.setName("Gigi");
        user.setEmail("Gigi@domain.com");
        entityManager.merge(user);
        entityTransaction.commit();



        // Example of HQL

        Query query=entityManager.createQuery("from User");
        List resultList=query.getResultList();

        // Example of HQL to get records with pagination
        query=entityManager.createQuery("from User ");
        query.setFirstResult(5);
        query.setMaxResults(10);
        resultList=query.getResultList ();//will return the records from 5 to 10th number



        /* Remove a User entity */
        entityTransaction.begin();
        user = entityManager.find(User.class, 1);
        entityManager.remove(user);
        entityTransaction.commit();

        entityManager.close();


    }

}
