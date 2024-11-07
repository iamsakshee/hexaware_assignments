package com.assignment.service;

import java.util.List;

import com.assignment.model.Customer;
import com.assignment.model.Product;
import com.assignment.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class PurchaseService {
    private EntityManager entityManager;

    public PurchaseService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    
    public boolean authenticateCustomer(String username, String password) {
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        List<User> users = query.getResultList();
        return !users.isEmpty(); 
    }

    
    public void displayAvailableProducts() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> products = query.getResultList();

        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            products.forEach(product -> System.out.println("ID: " + product.getId() + ", Name: " + product.getTitle()));
        }
    }

   
    public boolean purchaseProduct(String username, int productId, int quantity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
                     TypedQuery<Customer> customerQuery = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.user.username = :username", Customer.class);
            customerQuery.setParameter("username", username);
            Customer customer = customerQuery.getSingleResult();

            if (customer == null) {
                System.out.println("Customer not found.");
                return false;
            }

            
            Product product = entityManager.find(Product.class, productId);
            if (product == null) {
                System.out.println("Product not found.");
                return false;
            }

       
            if (product.getStockQty() < quantity) {
                System.out.println("Insufficient stock for the requested quantity.");
                return false;
            }

         
            transaction.begin();

            product.setStockQty(product.getStockQty() - quantity);
            entityManager.merge(product);

           
            System.out.println("Purchase successful for customer: " + customer.getName());

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
