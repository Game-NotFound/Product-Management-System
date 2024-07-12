package com.hauhh.repository;

import java.util.List;

import com.hauhh.pojo.Category;

public interface ICategoryRepository {

	public Category saveCategory(Category cate);
	
	public List<Category> listCategory();
	
	public Category getCategoryByName(String value);
}
