package com.pay.coeus.core.dubbo.inner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.api.inner.dubbo.CustomerActiveOrderFacade;
import com.pay.coeus.core.service.CustomerTransDayService;
import com.pay.coeus.model.entity.CustomerTransDay;
import com.pay.commons.utils.Page;
@Service("customerActiveOrderFacade")
public class CustomerActiveOrderFacadeImpl implements CustomerActiveOrderFacade{

	Logger logger = LoggerFactory.getLogger(CustomerActiveOrderFacadeImpl.class);

	@Autowired
	private CustomerTransDayService customerTransDayService;
	
	@Override
	public Map<String, String> getActiveCustomerData(String strTime, String endTime) {
		Map<String, String> returnMap = new HashMap<String, String>();
		try {
			returnMap.put("flag", "SUCCESS");
			returnMap.putAll(customerTransDayService.getActiveCustomerData(strTime, endTime));
			returnMap.putAll(customerTransDayService.getNewCustomerData(strTime, endTime));
//			returnMap.put("NEWJIHUONUM", "8888");
//			returnMap.put("NEWJIHUOPOSNUM", "9999");
			logger.info("getActiveCustomerData result1:"+returnMap.toString());
		} catch (Exception e) {
			returnMap.put("flag", "FAIL");
			logger.error("getActiveCustomerData 失败", e);
		}
		return returnMap;
	}

	@Override
	public Page<List<CustomerTransDay>> findAllList(Page<List<CustomerTransDay>> page, Map<String, String> param) {
		List<CustomerTransDay> list = customerTransDayService.findAllList(page, param);
		page.setObject(list);
		return page;
	}

	@Override
	public Page<List<Map<String, String>>> findAllCountList(Page<List<Map<String, String>>> page, Map<String, String> param) {
		List<Map<String, String>> list = customerTransDayService.findAllCountList(page, param);
		page.setObject(list);
		return page;
	}
	
	
	
}
