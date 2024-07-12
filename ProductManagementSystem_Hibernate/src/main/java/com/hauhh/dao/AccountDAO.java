package com.hauhh.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hauhh.pojo.Account;

public class AccountDAO {

	private SessionFactory sessionFactory = null;
	private Configuration config = null;

	public AccountDAO(String fileName) {
		config = new Configuration();
		config = config.configure(fileName);
		sessionFactory = config.buildSessionFactory();
	}

	public Account saveAccount(Account account) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.save(account);
			trans.commit();
			System.out.println("Successfully saved account");
			return account;
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error at AccountDAO saveAccount:" + e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Account> getListAccount() {
		List<Account> listAccount = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			trans.begin();
			Query<Account> query = session.createQuery("from Account", Account.class);
			listAccount = query.list();
			trans.commit();
			listAccount.add(session.get(Account.class, session));
		} catch (Exception e) {
			System.out.println("Error at AccountDAO - getListAccount: " + e.getMessage());
		} finally {
			session.close();
		}
		return listAccount;
	}

	public void deleteAccount(Long accountID) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			trans.begin();
			Account account = (Account) session.get(Account.class, accountID);
			session.delete(account);
			trans.commit();
			System.out.println("Delete account with ID: " + accountID + " successfully");
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error at AccountDAO - deleteAccount: " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void updateAccount(Account account) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.update(account);
			trans.commit();
			System.out.println("Update account with ID: " + account.getAccountID() + " successfully");
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error at AccountDAO - updateAccount: " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void updateAccountWithID(Long accountID, Account account) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			Account getAccount = session.get(Account.class, accountID);
			if (getAccount != null) {
				session.update(getAccount);
				trans.commit();
				System.out.println("Update account with ID: " + account.getAccountID() + " successfully");
			}
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
				System.out.println("Error at AccountDAO - updateAccountWithID: " + e.getMessage());
			}
		} finally {
			session.close();
		}
	}

	public Account getAccountWithID(Long accountID) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Account account = null;
		try {
			account = session.get(Account.class, accountID);
			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				System.out.println("Error at AccountDAO - getAccountWithID: " + e.getMessage());
				trans.rollback();
			}
		} finally {
			session.close();
		}
		return account;
	}

	@SuppressWarnings("unchecked")
	public Account login(String email, String password) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Account account = null;
		try {
			String HQL = "SELECT a FROM Account a WHERE a.email = :email AND a.password = :password";
			Query<Account> query = session.createQuery(HQL);
			query.setParameter("email", email);
			query.setParameter("password", password);

			account = query.uniqueResult();

			if (account != null) {
				if ("admin".equals(account.getRole())) {
					System.out.println("Login as admin");
				} else {
					System.out.println("Login as customer");
				}
			}
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error at Login" + e.getMessage());
			return null;
		} finally {
			session.close();
		}
		return account;
	}

}
