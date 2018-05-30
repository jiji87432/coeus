package com.pay.coeus.api.inner.dubbo;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.CustomerContactType;
import com.pay.commons.utils.Page;

/**
 * 有效联系方式来源
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactTypeFacade 此处填写需要参考的类
 * @version 2017年11月13日 上午2:36:28 
 * @author yuze.luo
 */
public interface CustomerContactTypeFacade {

	/**
	 * 分页列表查询
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param param
	 * @return
	 * @see 需要参考的类或方法
	 */
	Page<List<CustomerContactType>> findAllList(Page<List<CustomerContactType>> page, Map<String, String> param);

	/**
	 * 根据ID查询详细信息
	 * @Description  一句话描述方法用法
	 * @param id
	 * @return
	 * @see 需要参考的类或方法
	 */
	CustomerContactType findById(Long id);
	
	/**
	 * 添加或者修改信息
	 * @Description  一句话描述方法用法
	 * @param customerContactType
	 * @see 需要参考的类或方法
	 */
	void addOrModify(CustomerContactType customerContactType);
	
}
