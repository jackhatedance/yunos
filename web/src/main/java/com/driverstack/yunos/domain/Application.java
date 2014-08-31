package com.driverstack.yunos.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@javax.persistence.Entity
@Table
public class Application {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Column
	private String name;
	@Column
	private String description;

	/**
	 * any random string. it is a secret between the system and the app. usually
	 * the app send the hash value of the key+time stamp to server every
	 * request.
	 */
	@Column
	private String key;
	@Column
	private String authorName;
	@Column
	private String authorEmail;
	@Column
	private String authorPhone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getAuthorPhone() {
		return authorPhone;
	}

	public void setAuthorPhone(String authorPhone) {
		this.authorPhone = authorPhone;
	}

}
