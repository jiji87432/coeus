package com.pay.coeus.core.service;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.CustomerContactHistory;
import com.pay.commons.utils.Page;

/**
 * 商户联系方式服务
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactHistoryService 此处填写需要参考的类
 * @version 2017年11月15日 上午2:51:31 
 * @author yuze.luo
 */
public interface CustomerContactHistoryService{

	/**
	 * 直接添加联系方式
	 * @Description  一句话描述方法用法
	 * @param customerNo
	 * @param phone
	 * @param source
	 * @param remark
	 * @see 需要参考的类或方法
	 */
	public void addContactHistory(String customerNo, String customerRole, String phone, String source, String remark);

	/**
	 * 分页查询
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param param
	 * @return
	 * @see 需要参考的类或方法
	 */
	public List<CustomerContactHistory> findAllLList(Page<List<CustomerContactHistory>> page, Map<String, String> param);

	public List<Map<String, String>> getPhonesByCustomerNo(String customerNo);

}
