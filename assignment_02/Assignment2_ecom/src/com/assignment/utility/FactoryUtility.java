package com.assignment.utility;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class FactoryUtility {
	
	static FactoryUtility factoryUtility;
	
	static {
	factoryUtility =  new FactoryUtility();
	}
	
	public static FactoryUtility getInstance()
	{
		return factoryUtility; 	
	}
	
	private FactoryUtility() { }
	
	public EntityManager loadPersistence()
	{
		SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("ecom_assignment2");

		EntityManager entityManager = sessionFactory.createEntityManager();

		
		return entityManager;

	}

}
