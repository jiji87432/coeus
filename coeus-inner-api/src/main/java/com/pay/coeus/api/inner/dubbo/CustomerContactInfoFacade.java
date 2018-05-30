package com.pay.coeus.api.inner.dubbo;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.CustomerContactInfo;
import com.pay.commons.utils.Page;

/**
 * 有效联系方式
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactInfoFacade 此处填写需要参考的类
 * @version 2017年11月13日 上午2:36:15 
 * @author yuze.luo
 */
public interface CustomerContactInfoFacade {
	
	/**
	 * 分页列表查询
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param param
	 * @return
	 * @see 需要参考的类或方法
	 */
	Page<List<CustomerContactInfo>> findAllList(Page<List<CustomerContactInfo>> page, Map<String, String> param);

	/**
	 * 根据id查询
	 * @Description  一句话描述方法用法
	 * @param id
	 * @return
	 * @see 需要参考的类或方法
	 */
	CustomerContactInfo findById(Long id);

	/**
	 * 添加或修改
	 * @Description  一句话描述方法用法
	 * @param contactInfo
	 * @see 需要参考的类或方法
	 */
	void addOrModify(CustomerContactInfo contactInfo);

	/**
	 * 设置商户联系方式的身份
	 * @Description  一句话描述方法用法
	 * @param id
	 * @see 需要参考的类或方法
	 */
	void updateToAgent(Long id, String role);
	
}
