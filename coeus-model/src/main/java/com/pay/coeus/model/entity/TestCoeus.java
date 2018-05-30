package com.pay.coeus.model.entity;

import java.io.Serializable;

public class TestCoeus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3243242L;
	private Long id;
	private String name;
	private String value;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
