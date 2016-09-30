package net.abstractfactory.yunos.server.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * this user is only for backend manager, not for client user in front of phone.
 * 
 * @author jianghaoding
 *
 */
@Entity
public class ManagerUser {
	@Id
	private String id;

	/**
	 * hashed value
	 */
	private String passwordHash;

	private String role;

	private String firstName;
	private String lastName;
	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public Set<String> getRoleAsNames() {
		String[] names = role.split(",");
		Set<String> set = new HashSet<String>();
		for (String n : names)
			set.add(n);

		return set;
	}
}
