package com.hauhh.repository.impl;

import java.util.List;

import com.hauhh.dao.CategoryDAO;
import com.hauhh.pojo.Category;
import com.hauhh.repository.ICategoryRepository;

public class CategoryRepositoryImpl implements ICategoryRepository {

	private CategoryDAO categoryDAO = null;

	public CategoryRepositoryImpl(String fileName) {
		categoryDAO = new CategoryDAO(fileName);
	}

	@Override
	public Category saveCategory(Category cate) {
		return categoryDAO.saveCategory(cate);
	}

	@Override
	public List<Category> listCategory() {
		return categoryDAO.listCategory();
	}

	@Override
	public Category getCategoryByName(String value) {
		return categoryDAO.getCategoryByName(value);
	}

}
