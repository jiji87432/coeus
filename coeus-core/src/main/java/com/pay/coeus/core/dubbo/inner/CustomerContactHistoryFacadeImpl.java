package com.pay.coeus.core.dubbo.inner;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.api.inner.dubbo.CustomerContactHistoryFacade;
import com.pay.coeus.core.service.CustomerContactHistoryService;
//import com.pay.coeus.core.service.CustomerContactTypeService;
import com.pay.coeus.model.entity.CustomerContactHistory;
//import com.pay.coeus.model.entity.CustomerContactType;
import com.pay.commons.utils.Page;
@Service("customerContactHistoryFacade")
public class CustomerContactHistoryFacadeImpl implements CustomerContactHistoryFacade{

	Logger logger = LoggerFactory.getLogger(CustomerContactHistoryFacadeImpl.class);
	@Autowired
	private CustomerContactHistoryService customerContactHistoryService;
//	@Autowired
//	private CustomerContactTypeService customerContactTypeService;
	@Override
	public Page<List<CustomerContactHistory>> findAllList(Page<List<CustomerContactHistory>> page,
			Map<String, String> param) {
		List<CustomerContactHistory> list = customerContactHistoryService.findAllLList(page, param);
		/*if(list != null && list.size()>0){
			List<CustomerContactType> types = customerContactTypeService.getAllLList();
			if(types != null && list.size()>0){
				for(int i=0;i<list.size();i++){
					for(int j=0;j<types.size();j++){
						if(list.get(i).getSource() != null && types.get(j).getKeyword().equalsIgnoreCase(list.get(i).getSource())){
							list.get(i).setSource(types.get(j).getName());
							break;
						}
					}
				}
			}
		}*/
		page.setObject(list);
		return page;
	}
	
	@Override
	public void addOrModify(CustomerContactHistory contactHistory) {
		customerContactHistoryService.addContactHistory(contactHistory.getCustomerNo(), contactHistory.getCustomerRole()
				, contactHistory.getPhone(), contactHistory.getSource(), contactHistory.getRemark());
	}
	
}
