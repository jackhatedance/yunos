package com.deviceyun.yunos.api;

public class Parameter {
	private Class type;
	private String name;

	public Parameter(String name, Class type) {
		this.name = name;
		this.type = type;
	}

	public Class getType() {
		return type;
	}

	public void setType(Class type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
