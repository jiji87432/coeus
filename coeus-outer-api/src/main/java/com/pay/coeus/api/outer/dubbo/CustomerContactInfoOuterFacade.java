package com.pay.coeus.api.outer.dubbo;

import java.util.List;
import java.util.Map;

public interface CustomerContactInfoOuterFacade {
	
	/**
	 * 新增商户有效联系方式
	 * @Description  一句话描述方法用法
	 * @param customerNo 商编
	 * @param customerRole 商户身份（LEGAL法人、FINANCE会计、CASHIER收银员、AGENT服务商）
	 * @param phone 手机号码
	 * @param source 来源（调用服务的服务，例如KF客服/KYSF卡友商服/WX微信等）
	 * @param remark 备注，可为null
	 * @see 需要参考的类或方法
	 */
	public void addContactInfo(String customerNo, String customerRole, String phone
			, String source, String remark);
	
	/**
	 * 根据商编获取有效手机号
	 * 只返回手机号
	 * @Description  一句话描述方法用法
	 * @param customerNo
	 * @return
	 * @see 需要参考的类或方法
	 */
	public String getValidPhoneByCustomerNo(String customerNo);
	
	/**
	 * 根据商编获取对应有效手机号、身份等信息
	 * 只返回手机号
	 * @Description  一句话描述方法用法
	 * @param customerNo
	 * @return map.put("customerNo", cci.getCustomerNo());			8612356874
				map.put("customerRole", cci.getCustomerRole());		LEGAL/FINANCE/CASHIER/AGENT/null
				map.put("source", cci.getSource());					KF/HF/APP/WX等
				map.put("phone", cci.getPhone());					15685665895
				map.put("remark", cci.getRemark());
	 * @see 需要参考的类或方法
	 */
	public Map<String, String> getValidInfoByCustomerNo(String customerNo);
	
	/**
	 * 根据商编手机号沉淀记录
	 * 根据权重列出全部的沉淀记录
	 * @Description  一句话描述方法用法
	 * @param customerNo
	 * @return map.put("customerNo", cci.getCustomerNo());			8612356874
				map.put("customerRole", cci.getCustomerRole());		LEGAL/FINANCE/CASHIER/AGENT/null
				map.put("source", cci.getSource());					回访/客服/公众号/产品开通等
				map.put("phone", cci.getPhone());					15685665895
				map.put("remark", cci.getRemark());
	 * @see 需要参考的类或方法
	 */
	public List<Map<String, String>> getPhonesByCustomerNo(String customerNo);
	
	
	/**
	 * 根据手机号获取对应商编列表
	 * @Description  一句话描述方法用法
	 * @param phone
	 * @return
	 * @see 需要参考的类或方法
	 */
	public List<String> getCustomerNosByPhone(String phone);
	
	/**
	 * 根据手机号获取对应商编和身份列表
	 * @Description  一句话描述方法用法
	 * @param phone
	 * @return map.put("customerNo", cci.getCustomerNo());			8612356874
				map.put("customerRole", cci.getCustomerRole());		LEGAL/FINANCE/CASHIER/AGENT/null
				map.put("source", cci.getSource());					回访/客服/公众号/产品开通等
				map.put("phone", cci.getPhone());					15685665895
				map.put("remark", cci.getRemark());
	 * @see 需要参考的类或方法
	 */
	public List<Map<String, String>> getCustomerNoAndRoleByPhone(String phone);

}
