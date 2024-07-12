package com.hauhh.repository;

import java.util.List;

import com.hauhh.pojo.Account;

public interface IAccountRepository {
	
	public Account saveAccount(Account account);
	
	public List<Account> getListAccount();
	
	public void deleteAccount(Long accountID);
	
	public void updateAccount(Account account);
	
	public void updateAccountWithID(Long accountID, Account account);
	
	public Account getAccountWithID(Long accountID);
	
	public Account login(String email, String password);

}
