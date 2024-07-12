package com.hauhh.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hauhh.pojo.Product;

public class ProductDAO {

	private SessionFactory sessionFactory = null;
	private Configuration config = null;

	public ProductDAO(String fileName) {
		config = new Configuration();
		config = config.configure(fileName);
		sessionFactory = config.buildSessionFactory();
	}

	public Product saveObject(Product object) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.save(object);
			trans.commit();
			System.out.println("Successfully save object");
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error at saveObject: " + e.getMessage());
		} finally {
			session.close();
		}
		return object;
	}

	public List<Product> getList() {
		List<Product> list = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			Query<Product> query = session.createQuery("from Product", Product.class);
			list = query.list();
			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			System.out.println("Error at getList: " + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	public Product findByID(Long objectID) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Product object = null;
		try {
			object = session.get(Product.class, objectID);
			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				System.out.println("Error at findByID: " + e.getMessage());
				trans.rollback();
			}
		} finally {
			session.close();
		}
		return object;
	}

	public void delete(Long objectID) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			Product object = (Product) session.get(Product.class, objectID);
			session.delete(object);
			trans.commit();
			System.out.println("Delete successfully");
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error at delete " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void update(Product object) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.update(object);
			trans.commit();
			System.out.println("Update successfully");
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error at update: " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void updateWithID(Long objectID, Product object) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			Product getObject = session.get(Product.class, objectID);
			if (getObject != null) {
				session.update(getObject);
				trans.commit();
				System.out.println("Update successfully");
			}
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
				System.out.println("Error at updateWitID: " + e.getMessage());
			}
		} finally {
			session.close();
		}
	}

	public List<Product> searchProduct(String value) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		List<Product> searchList = new ArrayList<>();
		try {
			String HQL = "SELECT p FROM Product p WHERE p.productName LIKE :value";
			Query<Product> query = session.createQuery(HQL, Product.class);
			query.setParameter("value", "%" + value + "%");

			searchList = query.getResultList();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error at searchProduct");
		} finally {
			session.close();
		}
		return searchList;
	}
}
