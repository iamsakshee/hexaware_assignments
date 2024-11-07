package com.assignment.service;

import java.util.List;

import com.assignment.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class AuthService {

	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public AuthService(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.entityTransaction = entityManager.getTransaction();
	}

	public boolean validateUser(String username, String password) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Check if user exists
        String jpql = "SELECT u FROM User u WHERE u.username = ?1";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter(1, username);
        
        List<User> list = query.getResultList();
        entityTransaction.commit();

        // If the user list is empty, the user does not exist
        if (list.isEmpty()) {
            return false;
        }

        // If the user exists, check the password (assuming password comparison is handled securely)
        User user = list.get(0); // Assuming usernames are unique
        return user.getPassword().equals(password); // Replace with secure password comparison if needed
    }

}
