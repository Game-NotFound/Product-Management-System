package com.hauhh.pojo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productID;

	private String productName;

	private String description;

	private double price;

	private int quantity;

	@ManyToOne
	@JoinColumn(name = "categoryID")
	private Category categoryID;

	private LocalDate dateCreated;

	public Product(Long productID, String productName, String description, double price, int quantity,
			Category categoryID, LocalDate dateCreated) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.categoryID = categoryID;
		this.dateCreated = dateCreated;
	}

	public Product(String productName, String description, double price, int quantity, Category categoryID,
			LocalDate dateCreated) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.categoryID = categoryID;
		this.dateCreated = dateCreated;
	}

	public Product() {
		super();
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Category categoryID) {
		this.categoryID = categoryID;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

}
