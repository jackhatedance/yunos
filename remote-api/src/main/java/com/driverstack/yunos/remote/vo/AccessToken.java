package com.driverstack.yunos.remote.vo;

import java.io.Serializable;

/**
 * 
 * 
 * @author jack
 * 
 */
public class AccessToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2769976885456199417L;

	private String key;

	private String secret;

	public AccessToken() {

	}

	public AccessToken(String key, String secret) {
		this.key = key;
		this.secret = secret;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
