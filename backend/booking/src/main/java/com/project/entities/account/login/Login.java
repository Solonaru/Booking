package com.project.entities.account.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.entities.account.Account;

@Entity
public class Login {

	@Id
	@Column(name = "username", updatable = false, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@JsonIgnoreProperties(value = "login")
	@OneToOne
	@JoinColumn(name = "account")
	private Account account;

	/* ----- CONSTRUCTORS ----- */
	public Login() {
		super();
	}

	public Login(String username, String password, Account account) {
		super();
		this.username = username;
		this.password = password;
		this.account = account;
	}

	/* ----- GETTERS & SETTERS ----- */
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

}
