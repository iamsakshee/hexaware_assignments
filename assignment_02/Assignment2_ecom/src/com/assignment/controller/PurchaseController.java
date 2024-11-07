package com.assignment.controller;

import java.util.Scanner;
import java.util.List;

import com.assignment.service.PurchaseService;
import com.assignment.utility.FactoryUtility;

import jakarta.persistence.EntityManager;

public class PurchaseController {
    public static void main(String[] args) {
        EntityManager entityManager = FactoryUtility.getInstance().loadPersistence();
        Scanner sc = new Scanner(System.in);
        
        PurchaseService purchaseService = new PurchaseService(entityManager);

        
        System.out.print("Enter customer username: ");
        String username = sc.nextLine();
        
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        
        boolean isAuthenticated = purchaseService.authenticateCustomer(username, password);
        if (!isAuthenticated) {
            System.out.println("Invalid username or password. Exiting.");
            return;
        }

    
        System.out.println("Available Products:");
        purchaseService.displayAvailableProducts();

        
        System.out.print("Enter product ID to purchase: ");
        int productId = sc.nextInt();

        System.out.print("Enter quantity to purchase: ");
        int quantity = sc.nextInt();

       
        purchaseService.purchaseProduct(username, productId, quantity);
    }
}
