package com.pay.coeus.core.dubbo.inner;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.api.inner.dubbo.CustomerContactInfoFacade;
import com.pay.coeus.core.service.CustomerContactInfoService;
//import com.pay.coeus.core.service.CustomerContactTypeService;
import com.pay.coeus.model.entity.CustomerContactInfo;
//import com.pay.coeus.model.entity.CustomerContactType;
import com.pay.commons.utils.Page;
@Service("customerContactInfoFacade")
public class CustomerContactInfoFacadeImpl implements CustomerContactInfoFacade{

	Logger logger = LoggerFactory.getLogger(CustomerContactInfoFacadeImpl.class);
	@Autowired
	private CustomerContactInfoService customerContactInfoService;
//	@Autowired
//	private CustomerContactTypeService customerContactTypeService;
	@Override
	public Page<List<CustomerContactInfo>> findAllList(Page<List<CustomerContactInfo>> page,
			Map<String, String> param) {
		List<CustomerContactInfo> list = customerContactInfoService.findAllLList(page, param);
//		if(list != null && list.size()>0){
//			List<CustomerContactType> types = customerContactTypeService.getAllLList();
//			if(types != null && list.size()>0){
//				for(int i=0;i<list.size();i++){
//					for(int j=0;j<types.size();j++){
//						if(list.get(i).getSource() != null && types.get(j).getKeyword().equalsIgnoreCase(list.get(i).getSource())){
//							list.get(i).setSource(types.get(j).getName());
//							break;
//						}
//					}
//				}
//			}
//		}
		page.setObject(list);
		return page;
	}
	@Override
	public CustomerContactInfo findById(Long id) {
		return customerContactInfoService.findById(id);
	}
	@Override
	public void addOrModify(CustomerContactInfo contactInfo) {
		customerContactInfoService.addContactInfo(contactInfo.getCustomerNo(), contactInfo.getCustomerRole()
				, contactInfo.getPhone(), contactInfo.getSource(), contactInfo.getRemark());
	}
	@Override
	public void updateToAgent(Long id, String role) {
		customerContactInfoService.updateToAgent(id, role);
	}
	
}
