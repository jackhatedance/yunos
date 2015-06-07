package com.driverstack.yunos.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

@javax.persistence.Entity
public class User {
	@Id
	private String id;
	@Column
	private String password;

	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String email;

	public User() {

	}

	public User(String id, String password, String firstName, String lastName,
			String email) {
		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
