package com.pay.coeus.api.inner.dubbo;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.CustomerContactHistory;
import com.pay.commons.utils.Page;

/**
 * 联系方式沉淀记录
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactHistoryFacade 此处填写需要参考的类
 * @version 2017年11月15日 上午2:36:15 
 * @author yuze.luo
 */
public interface CustomerContactHistoryFacade {
	
	/**
	 * 分页列表查询
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param param
	 * @return
	 * @see 需要参考的类或方法
	 */
	Page<List<CustomerContactHistory>> findAllList(Page<List<CustomerContactHistory>> page, Map<String, String> param);

	/**
	 * 添加或修改
	 * @Description  一句话描述方法用法
	 * @param contactInfo
	 * @see 需要参考的类或方法
	 */
	void addOrModify(CustomerContactHistory contactHistory);
	
}
