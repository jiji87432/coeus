package com.pay.coeus.model.entity;

import java.util.Date;

public class CustomerContactType extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8006977657679254537L;
	private String name;
	private String keyword;
	// 权重，越大越有效
	private Integer rank;
	// 1有效0无效
	private Integer type;
	private Date createTime;
	private Date updateTime;
	private String remark;
	
	public CustomerContactType() {
		super();
	}

	public CustomerContactType(String name, String keyword, Integer rank, Integer type, Date createTime,
			Date updateTime, String remark) {
		super();
		this.name = name;
		this.keyword = keyword;
		this.rank = rank;
		this.type = type;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "CustomerContactType [name=" + name + ", keyword=" + keyword + ", rank=" + rank + ", type=" + type
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", remark=" + remark + "]";
	}
}
