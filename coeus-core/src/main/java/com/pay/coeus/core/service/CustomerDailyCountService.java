package com.pay.coeus.core.service;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.CustomerDailyCount;
import com.pay.commons.utils.Page;

/**
 * 日活
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerDailyCountService 此处填写需要参考的类
 * @version 2017年11月9日 上午10:04:40 
 * @author yuze.luo
 */
public interface CustomerDailyCountService{
	
	/**
	 * 统计日活
	 * @Description  一句话描述方法用法
	 * @param date yyyy-MM-dd
	 * @see 需要参考的类或方法
	 */
	public void countCustomerDaily(String date);
	
	public List<CustomerDailyCount> findAllLList(Page<List<CustomerDailyCount>> page, Map<String, String> param);

}
