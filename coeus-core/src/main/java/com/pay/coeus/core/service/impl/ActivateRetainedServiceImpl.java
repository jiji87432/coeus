package com.pay.coeus.core.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.core.dao.boss.CustomerNewOldInfoMapper;
import com.pay.coeus.core.dao.boss.MonthTradeFrequencyMapper;
import com.pay.coeus.core.service.ActivateRetainedService;

@Service
public class ActivateRetainedServiceImpl implements ActivateRetainedService {
	
	@Autowired
	private MonthTradeFrequencyMapper monthTradeFrequencyMapper;
	@Autowired
	private CustomerNewOldInfoMapper customerNewOldInfoMapper;
	
	@Override
	public Map<String,Object> loadActivateRetainedData(Integer month){
		List<Map<String, Object>> retainedCustomerList = monthTradeFrequencyMapper.loadRetainedCustomer(month);
		Map<String,Object> loadRetainedCustomer = retainedCustomerList.get(0);
		List<Map<String, Object>> customerActivitiTimeList = customerNewOldInfoMapper.loadCustomerActivitiTime(month);
		Map<String, Object> loadCustomerActivitiTime = customerActivitiTimeList.get(0);
		loadRetainedCustomer.putAll(loadCustomerActivitiTime);
		return loadRetainedCustomer;
	}

}
