package com.pay.coeus.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.core.dao.boss.CustomerNewOldInfoMapper;
import com.pay.coeus.core.mqProducer.CustomerActivateProducer;
import com.pay.coeus.core.service.CustomerNewOldInfoService;

@Service
public class CustomerNewOldInfoServiceImpl implements CustomerNewOldInfoService {
	
	Logger logger = LoggerFactory.getLogger(CustomerNewOldInfoServiceImpl.class);
	
	@Autowired
	private CustomerNewOldInfoMapper customerNewOldInfoMapper;
	
	@Autowired
	private CustomerActivateProducer customerActivateProducer;
	
	@Override
	public int loadCustomerNewOpen(){
		return customerNewOldInfoMapper.insertNewOpenCustomerNo();
	}

	@Override
	public void updateBindAndActivateTime() {
		int bindTime = customerNewOldInfoMapper.updateBindTime();
		logger.info("updateBindTime record {}",bindTime);
		int activateTime = customerNewOldInfoMapper.updateActivateTime();
		logger.info("updateActivateTime record {}",activateTime);
	}
	
	@Override
	public int loadCustomerActivateTimeDay(){

		int start = 1;
		int pageLimit = 999;
		int sum = 0;
		while(true){
			
			List<Map<String,Object>> customerTime = customerNewOldInfoMapper.loadCustomerDayActivitiTime(start, start+pageLimit);
			if(customerTime != null && !customerTime.isEmpty()){
				logger.info("customerLoss.size {} ", customerTime.size());
				for (Map<String, Object> map : customerTime) {
					customerActivateProducer.sendMsg(map);
				}
				sum = sum +  customerTime.size();
				if(customerTime.size() != (pageLimit+1)){
					//如果和总条数不相等 说明最后一页
					break;
				}
			}else {
				break;
			}
			start = start+pageLimit+1;
		}
		return sum;
		
	}
	
}
