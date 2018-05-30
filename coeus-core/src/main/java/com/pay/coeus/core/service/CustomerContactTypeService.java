package com.pay.coeus.core.service;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.CustomerContactType;
import com.pay.commons.utils.Page;

/**
 * 商户联系方式服务
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactInfoService 此处填写需要参考的类
 * @version 2017年11月6日 上午2:51:31 
 * @author yuze.luo
 */
public interface CustomerContactTypeService{
	/**
	 * 列表查询
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param param
	 * @return
	 * @see 需要参考的类或方法
	 */
	public List<CustomerContactType> findAllLList(Page<List<CustomerContactType>> page, Map<String, String> param);
	
	/**
	 * 获取全部列表
	 * @Description  一句话描述方法用法
	 * @return
	 * @see 需要参考的类或方法
	 */
	public List<CustomerContactType> getAllLList();
	
	/**
	 * 根据ID查询详情
	 * @Description  一句话描述方法用法
	 * @param id
	 * @return
	 * @see 需要参考的类或方法
	 */
	public CustomerContactType findById(Long id);

	/**
	 * 根据id修改详情
	 * @Description  一句话描述方法用法
	 * @param customerContactType
	 * @see 需要参考的类或方法
	 */
	public void addOrModify(CustomerContactType customerContactType);
}
