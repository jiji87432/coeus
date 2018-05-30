package com.pay.coeus.core.service;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.NewCustomerActivate;
import com.pay.commons.utils.Page;

public interface NewCustomerActivateService {
	
	/**
	 * 绑定未激活
	 * @return
	 */
	public int loadNewCustomerBindNoActivate(Integer day);

	/**
	 * 激活未留存
	 * @return
	 */
	public int loadNewCustomerActivateNoRemain(Integer day);
	/**
	 * 分页查询NewCustomerActivate
	 * @param page
	 * @param param
	 * @return
	 */
	List<NewCustomerActivate> findAllList(Page<List<NewCustomerActivate>> page,Map<String,String> param);
	
	/**
	 * 激活留存是否交易
	 * @param day
	 * @return
	 */
	public int loadNewCustomerActivateTrans(Integer day);
}
