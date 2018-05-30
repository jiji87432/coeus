package com.pay.coeus.model.entity;

import java.io.Serializable;

public class BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 960120374595286280L;
	
	private Long id;
	private String optimistic;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOptimistic() {
		return optimistic;
	}
	public void setOptimistic(String optimistic) {
		this.optimistic = optimistic;
	}
	
	

}
