package com.assignment.controller;

import java.util.List;
import java.util.Scanner;

import com.assignment.model.Customer;
import com.assignment.service.CustomerService;
import com.assignment.utility.FactoryUtility;

import jakarta.persistence.EntityManager;

public class CustomerController {

	public static void main(String[] args) {
		EntityManager entityManager = FactoryUtility.getInstance().loadPersistence();
		Scanner sc = new Scanner(System.in);
		CustomerService customerService = new CustomerService(entityManager, entityManager.getTransaction());

		while (true) {
			System.out.println("Main menu");
			System.out.println("1. Insert Customer with Address");
			System.out.println("2. Fetch Customer Details with address");
			System.out.println("0. To Exit");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting..Thankyou!!");
				break;
			}

			switch (input) {
			case 1:

				Customer customer = customerService.takeInputAndGenerateId(sc);

				customerService.saveAddress(customer.getAddress());
				customerService.saveUser(customer.getUser());

				// save customer
				customerService.saveCustomer(customer);
				System.out.println("Customer with address added to DB");
				break;

			case 2:
				List<Customer> list = customerService.fetchAllCustomer();
				list.stream().forEach(c -> System.out.println(c));
				break;

			default:
				System.out.println("Invalid Input, Try Again!!");
				break;
			}

		}
	}

}
