package com.pay.coeus.core.dubbo.inner;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.api.inner.dubbo.CustomerDailyCountFacade;
import com.pay.coeus.core.service.CustomerDailyCountService;
import com.pay.coeus.model.entity.CustomerDailyCount;
import com.pay.commons.utils.Page;
@Service("customerDailyCountFacade")
public class CustomerDailyCountFacadeImpl implements CustomerDailyCountFacade{

	@Autowired
	private CustomerDailyCountService customerDailyCountService;

	@Override
	public Page<List<CustomerDailyCount>> findAllList(Page<List<CustomerDailyCount>> page, Map<String, String> param) {
		List<CustomerDailyCount> list = customerDailyCountService.findAllLList(page, param);
		page.setObject(list);
		return page;
	}
	
}
