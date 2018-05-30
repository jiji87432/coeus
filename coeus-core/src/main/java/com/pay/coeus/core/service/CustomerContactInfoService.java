package com.pay.coeus.core.service;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.CustomerContactInfo;
import com.pay.commons.utils.Page;

/**
 * 商户联系方式服务
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactInfoService 此处填写需要参考的类
 * @version 2017年11月6日 上午2:51:31 
 * @author yuze.luo
 */
public interface CustomerContactInfoService{
	
	/**
	 * 处理队列
	 * @Description  一句话描述方法用法
	 * @see 需要参考的类或方法
	 */
//	public void dealContactInfoQueue();

	/**
	 * 直接添加联系方式
	 * @Description  一句话描述方法用法
	 * @param customerNo
	 * @param phone
	 * @param source
	 * @param remark
	 * @see 需要参考的类或方法
	 */
	public void addContactInfo(String customerNo, String customerRole, String phone, String source, String remark);

	/**
	 * 根据商编查询有效联系方式
	 * @Description  一句话描述方法用法
	 * @param customerNo
	 * @return
	 * @see 需要参考的类或方法
	 */
	public String getValidPhoneByCustomerNo(String customerNo);
	
	/**
	 * 获取有效联系信息
	 * @Description  一句话描述方法用法
	 * @param customerNo
	 * @return
	 * @see 需要参考的类或方法
	 */
	public Map<String, String> getValidInfoByCustomerNo(String customerNo);

	/**
	 * 根据联系方式查询商编列表
	 * @Description  一句话描述方法用法
	 * @param phone
	 * @return
	 * @see 需要参考的类或方法
	 */
	public List<String> getCustomerNosByPhone(String phone);
	
	/**
	 * 分页查询
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param param
	 * @return
	 * @see 需要参考的类或方法
	 */
	public List<CustomerContactInfo> findAllLList(Page<List<CustomerContactInfo>> page, Map<String, String> param);

	/**
	 * 根据ID获取详情
	 * @Description  一句话描述方法用法
	 * @param id
	 * @return
	 * @see 需要参考的类或方法
	 */
	public CustomerContactInfo findById(Long id);

	/**
	 * 根据联系方式查询商编和身份
	 * @Description  一句话描述方法用法
	 * @param phone
	 * @return
	 * @see 需要参考的类或方法
	 */
	public List<Map<String, String>> getCustomerNoAndRoleByPhone(String phone);

	/**
	 * 修改身份
	 * @Description  一句话描述方法用法
	 * @param id
	 * @param role
	 * @see 需要参考的类或方法
	 */
	public void updateToAgent(Long id, String role);

}
