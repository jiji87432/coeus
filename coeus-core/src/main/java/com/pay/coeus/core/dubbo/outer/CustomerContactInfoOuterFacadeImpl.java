package com.pay.coeus.core.dubbo.outer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.coeus.api.outer.dubbo.CustomerContactInfoOuterFacade;
import com.pay.coeus.core.service.CustomerContactHistoryService;
import com.pay.coeus.core.service.CustomerContactInfoService;
@Service("customerContactInfoOuterFacade")
public class CustomerContactInfoOuterFacadeImpl implements CustomerContactInfoOuterFacade {

	@Resource
	private CustomerContactInfoService customerContactInfoService;
	@Resource
	private CustomerContactHistoryService customerContactHistoryService;
	
	@Override
	public void addContactInfo(String customerNo, String customerRole, String phone , String source, String remark) {
		customerContactInfoService.addContactInfo(customerNo, customerRole, phone, source, remark);
	}
	@Override
	public String getValidPhoneByCustomerNo(String customerNo) {
		return customerContactInfoService.getValidPhoneByCustomerNo(customerNo);
	}
	
	@Override
	public Map<String, String> getValidInfoByCustomerNo(String customerNo) {
		return customerContactInfoService.getValidInfoByCustomerNo(customerNo);
	}
	
	@Override
	public List<String> getCustomerNosByPhone(String phone) {
		return customerContactInfoService.getCustomerNosByPhone(phone);
	}
	@Override
	public List<Map<String, String>> getCustomerNoAndRoleByPhone(String phone) {
		return customerContactInfoService.getCustomerNoAndRoleByPhone(phone);
	}

	@Override
	public List<Map<String, String>> getPhonesByCustomerNo(String customerNo) {
		return customerContactHistoryService.getPhonesByCustomerNo(customerNo);
	}
	
}
