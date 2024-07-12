package com.hauhh.repository;

import java.util.List;

import com.hauhh.pojo.Product;

public interface IProductRepository {
	
	public Product saveProduct(Product product);
	
	public List<Product> listProduct();
	
	public Product getProductByID(Long productID);
	
	public void deleteProduct(Long productID);
	
	public void updateProduct(Product product);
	
	public void updateProductByID(Long productID, Product product);
	
	public List<Product> searchProduct(String value);

}
