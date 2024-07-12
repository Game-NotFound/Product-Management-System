package com.hauhh.repository.impl;

import java.util.List;

import com.hauhh.dao.ProductDAO;
import com.hauhh.pojo.Product;
import com.hauhh.repository.IProductRepository;

public class ProductRepositoryImpl implements IProductRepository {

	private ProductDAO productDAO = null;

	public ProductRepositoryImpl(String fileName) {
		productDAO = new ProductDAO(fileName);
	}

	@Override
	public Product saveProduct(Product product) {
		return productDAO.saveObject(product);
	}

	@Override
	public List<Product> listProduct() {
		return productDAO.getList();
	}

	@Override
	public Product getProductByID(Long productID) {
		return productDAO.findByID(productID);
	}

	@Override
	public void deleteProduct(Long productID) {
		productDAO.delete(productID);
	}

	@Override
	public void updateProduct(Product product) {
		productDAO.update(product);
	}

	@Override
	public void updateProductByID(Long productID, Product product) {
		productDAO.updateWithID(productID, product);
	}

	@Override
	public List<Product> searchProduct(String value) {
		return productDAO.searchProduct(value);
	}

}
