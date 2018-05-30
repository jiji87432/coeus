package com.pay.coeus.core.dubbo.inner;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.api.inner.dubbo.CustomerContactTypeFacade;
import com.pay.coeus.core.service.CustomerContactTypeService;
import com.pay.coeus.model.entity.CustomerContactType;
import com.pay.commons.utils.Page;
@Service("customerContactTypeFacade")
public class CustomerContactTypeFacadeImpl implements CustomerContactTypeFacade{

	@Autowired
	private CustomerContactTypeService customerContactTypeService;
	
	@Override
	public Page<List<CustomerContactType>> findAllList(Page<List<CustomerContactType>> page,
			Map<String, String> param) {
		List<CustomerContactType> list = customerContactTypeService.findAllLList(page, param);
		page.setObject(list);
		return page;
	}

	@Override
	public CustomerContactType findById(Long id) {
		return customerContactTypeService.findById(id);
	}

	@Override
	public void addOrModify(CustomerContactType customerContactType) {
		customerContactTypeService.addOrModify(customerContactType);
	}
	
	
	
}
