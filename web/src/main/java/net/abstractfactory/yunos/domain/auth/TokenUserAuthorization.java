package net.abstractfactory.yunos.domain.auth;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import net.abstractfactory.yunos.domain.User;

@javax.persistence.Entity
public class TokenUserAuthorization {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@ManyToOne
	@JoinColumn(name = "tokenId")
	private Token token;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@Type(type = "yes_no")
	private boolean readable;
	@Type(type = "yes_no")
	private boolean writable;
	@Type(type = "yes_no")
	private boolean executable;
	@Type(type = "yes_no")
	private boolean deletable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isReadable() {
		return readable;
	}

	public void setReadable(boolean readable) {
		this.readable = readable;
	}

	public boolean isWritable() {
		return writable;
	}

	public void setWritable(boolean writable) {
		this.writable = writable;
	}

	public boolean isExecutable() {
		return executable;
	}

	public void setExecutable(boolean executable) {
		this.executable = executable;
	}

	public boolean isDeletable() {
		return deletable;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

	public TokenUserAuthorization() {

	}

	public TokenUserAuthorization(User user, boolean readable,
			boolean writable, boolean executable, boolean deletable) {
		this.user = user;
		this.readable = readable;
		this.writable = writable;
		this.executable = executable;
		this.deletable = deletable;
	}
}
