package com.pay.coeus.api.inner.bean;

import java.io.Serializable;

/**
 * 
 * <p>Title:ReturnConstant</p>
 * <p>Description:返回结果类</p>
 * @author yongda.ren
 * @date 2017年11月3日 上午11:48:33
 * @param <T>
 */
public class ReturnConstant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5174867153525505458L;
	
	private Integer code;
	
	private String type;
	
	private String msg;
	
	private Object data;
	
	public ReturnConstant() {
	}

	public ReturnConstant(Integer code, String type, String msg, Object data) {
		this.code = code;
		this.type = type;
		this.msg = msg;
		this.data = data;
	}
	
	public ReturnConstant(Integer code, String type, Object data) {
		this.code = code;
		this.type = type;
		this.data = data;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ReturnConstant [code=" + code + ", msg=" + msg + ", data="
				+ data + "]";
	}
	
}
