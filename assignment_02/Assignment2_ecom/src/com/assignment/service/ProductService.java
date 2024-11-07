package com.assignment.service;

import java.util.List;
import java.util.Scanner;

import com.assignment.exception.InvalidIdException;
import com.assignment.model.Category;
import com.assignment.model.Product;
import com.assignment.model.Vendor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class ProductService {
    
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public ProductService(EntityManager entityManager, EntityTransaction entityTransaction) {
        this.entityManager = entityManager;
        this.entityTransaction = entityTransaction;
    }

    public Product takeInputAndGenerateId(Scanner sc) {
        Product product = new Product();
        System.out.println("Enter Product Title");
        sc.nextLine();
        product.setTitle(sc.nextLine());
        System.out.println("Enter description");
        product.setDescription(sc.nextLine());
        System.out.println("Enter price");
        product.setPrice(sc.nextDouble());

        // Ask for stock quantity
        System.out.println("Enter stock quantity");
        product.setStockQty(sc.nextInt());

        int productId = (int)(Math.random()*10000000);
        product.setId(productId);
        
        System.out.println("------------vendor info--------------");
        Vendor vendor = new Vendor();
        System.out.println("Enter name");
        sc.nextLine();
        vendor.setName(sc.nextLine());
        System.out.println("Enter city");
        vendor.setCity(sc.nextLine());
        int vendorId = (int) (Math.random()*10000000);
        vendor.setId(vendorId);
        product.setVendor(vendor);
        
        System.out.println("------------Category info--------------");
        Category category = new Category();
        System.out.println("Enter name");
        category.setName(sc.next());
        System.out.println("Enter sequence");
        category.setSequence(sc.nextInt());
        int categoryId = (int) (Math.random()*10000000);
        category.setId(categoryId);
        product.setCategory(category);

        return product;
    }

    public void insert(Product product) {
        entityTransaction.begin();
        entityManager.persist(product);
        entityTransaction.commit();
    }

    public Product getById(Scanner sc) throws InvalidIdException {
        entityTransaction.begin();
        System.out.println("Enter Product Id");
        int id = sc.nextInt();
        Product product = entityManager.find(Product.class, id);
        if(product == null) {
            throw new InvalidIdException("Product Id Invalid ...");
        }
        entityTransaction.commit();
        
        return product;
    }

    public void delete(Product product) {
        entityTransaction.begin();
        entityManager.remove(product);
        entityTransaction.commit();
    }

    public List<Product> getAll(){
        entityTransaction.begin();
        String jpql = "select p from Product p";
        TypedQuery<Product> typedQuery = entityManager.createQuery(jpql, Product.class);
        List<Product> list = typedQuery.getResultList();
        entityTransaction.commit();
        
        return list;
    }

    public void saveVendor(Vendor vendor) {
        entityTransaction.begin();
        entityManager.persist(vendor);
        entityTransaction.commit();
    }

    public void saveCategory(Category category) {
        entityTransaction.begin();
        entityManager.persist(category);
        entityTransaction.commit();
    }

    public void saveProduct(Product product) {
        entityTransaction.begin();
        entityManager.persist(product);
        entityTransaction.commit();
    }

    public List<Product> getAllUsingHQL() {
        entityTransaction.begin();
        String hql = "from Product";
        TypedQuery<Product> typedQuery = entityManager.createQuery(hql, Product.class);
        List<Product> list = typedQuery.getResultList();
        entityTransaction.commit();
        
        return list;
    }

    public List<Product> getAllUsingCriteriaQuery() {
        entityTransaction.begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> rootProduct = criteriaQuery.from(Product.class);
        criteriaQuery.select(rootProduct);
        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);
        entityTransaction.commit();
        
        return query.getResultList();
    }
}
