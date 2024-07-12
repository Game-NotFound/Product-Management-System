package com.hauhh.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountID;
	
	private String email;
	
	private String password;
	
	private String role;

	public Account(Long accountID, String email, String password, String role) {
		super();
		this.accountID = accountID;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Account(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Account() {
		super();
	}

	public Long getAccountID() {
		return accountID;
	}

	public void setAccountID(Long accountID) {
		this.accountID = accountID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
