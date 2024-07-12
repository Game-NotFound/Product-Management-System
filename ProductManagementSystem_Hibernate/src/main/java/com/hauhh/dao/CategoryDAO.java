package com.hauhh.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hauhh.pojo.Category;

public class CategoryDAO {

	private SessionFactory sessionFactory = null;
	private Configuration config = null;

	public CategoryDAO(String fileName) {
		config = new Configuration();
		config = config.configure(fileName);
		sessionFactory = config.buildSessionFactory();
	}

	public Category saveCategory(Category category) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.save(category);
			trans.commit();
			System.out.println("Successfully saved Category");
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error at CategoryDAO - saveCategory: " + e.getMessage());
		} finally {
			session.close();
		}
		return category;
	}

	public List<Category> listCategory() {
		List<Category> list = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			Query<Category> query = session.createQuery("FROM Category", Category.class);
			list = query.list();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error at CategoryDAO - listCategory: " + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	public Category getCategoryByName(String categoryName) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Category category = null;
		try {
			Query<Category> query = session.createQuery("FROM Category WHERE categoryName = :categoryName",
					Category.class);
			query.setParameter("categoryName", categoryName);
			category = query.uniqueResult();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error at CategoryDAO - getCategoryByName" + e.getMessage());
		} finally {
			session.close();
		}
		return category;
	}

}
