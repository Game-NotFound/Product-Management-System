package com.hauhh.repository.impl;

import java.util.List;

import com.hauhh.dao.AccountDAO;
import com.hauhh.pojo.Account;
import com.hauhh.repository.IAccountRepository;

public class AccountRepositoryImpl implements IAccountRepository {

	private AccountDAO accountDAO = null;

	public AccountRepositoryImpl(String fileName) {
		accountDAO = new AccountDAO(fileName);
	}

	@Override
	public Account saveAccount(Account account) {
		return accountDAO.saveAccount(account);
	}

	@Override
	public List<Account> getListAccount() {
		return accountDAO.getListAccount();
	}

	@Override
	public void deleteAccount(Long accountID) {
		accountDAO.deleteAccount(accountID);
	}

	@Override
	public void updateAccount(Account account) {
		accountDAO.updateAccount(account);
	}

	@Override
	public void updateAccountWithID(Long accountID, Account account) {
		accountDAO.updateAccountWithID(accountID, account);
	}

	@Override
	public Account getAccountWithID(Long accountID) {
		return accountDAO.getAccountWithID(accountID);
	}

	@Override
	public Account login(String email, String password) {
		return accountDAO.login(email, password);
	}

}
