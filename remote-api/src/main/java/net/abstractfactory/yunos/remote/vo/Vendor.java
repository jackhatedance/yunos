package net.abstractfactory.yunos.remote.vo;

import java.io.Serializable;

/**
 * it is a entity.
 * 
 * @author jack
 * 
 */
public class Vendor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1772551256715574984L;

	private String id;

	private String shortName;

	private String fullNameName;

	private String description;

	public Vendor() {

	}

	public Vendor(String id,

	String shortName,

	String fullNameName,

	String description) {
		this.id = id;
		this.shortName = shortName;
		this.fullNameName = fullNameName;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullNameName() {
		return fullNameName;
	}

	public void setFullNameName(String fullNameName) {
		this.fullNameName = fullNameName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
