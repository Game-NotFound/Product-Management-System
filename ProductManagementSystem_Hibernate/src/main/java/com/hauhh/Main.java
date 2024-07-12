package com.hauhh;

import java.time.LocalDate;

import com.hauhh.dao.AccountDAO;
import com.hauhh.dao.CategoryDAO;
import com.hauhh.dao.ProductDAO;
import com.hauhh.pojo.Account;
import com.hauhh.pojo.Category;
import com.hauhh.pojo.Product;

public class Main {
	public static void main(String[] args) {
		//Initial sample data
		AccountDAO accountDAO = new AccountDAO("hibernate.cfg.xml");
		CategoryDAO categoryDAO = new CategoryDAO("hibernate.cfg.xml");
		ProductDAO productDAO = new ProductDAO("hibernate.cfg.xml");
		
		Account adminAccount = new Account("admin@example.com", "123", "admin");
		Account admin2Account = new Account("1", "1", "admin");
		Account userAccount = new Account("user@example.com", "123", "user");
		
		accountDAO.saveAccount(userAccount);
		accountDAO.saveAccount(adminAccount);
		accountDAO.saveAccount(admin2Account);
		
		Category category = new Category("Electronics");
		Category category2 = new Category("Books");
		
		categoryDAO.saveCategory(category2);
		categoryDAO.saveCategory(category);
		
		 Product product1 = new Product();
         product1.setProductName("Smartphone");
         product1.setDescription("Latest model smartphone");
         product1.setPrice(699.99);
         product1.setQuantity(50);
         product1.setCategoryID(category);
         product1.setDateCreated(LocalDate.now());

         Product product2 = new Product();
         product2.setProductName("Laptop");
         product2.setDescription("High-performance laptop");
         product2.setPrice(1199.99);
         product2.setQuantity(30);
         product2.setCategoryID(category);
         product2.setDateCreated(LocalDate.now());

         Product product3 = new Product();
         product3.setProductName("Book Title");
         product3.setDescription("Interesting book about programming");
         product3.setPrice(29.99);
         product3.setQuantity(100);
         product3.setCategoryID(category2);
         product3.setDateCreated(LocalDate.now());
         
         productDAO.saveObject(product3);
         productDAO.saveObject(product1);
         productDAO.saveObject(product2);
	}
}
