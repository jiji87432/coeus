package com.pay.coeus.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.core.dao.boss.CustomerNewOldInfoMapper;
import com.pay.coeus.core.dao.boss.CustomerOrderQueryMapper;
import com.pay.coeus.core.dao.coeus.NewCustomerActivateMapper;
import com.pay.coeus.core.service.NewCustomerActivateService;
import com.pay.coeus.model.entity.NewCustomerActivate;
import com.pay.commons.utils.Page;

@Service
public class NewCustomerActivateServiceImpl implements NewCustomerActivateService {
	
	Logger logger = LoggerFactory.getLogger(NewCustomerActivateServiceImpl.class);
	
	@Autowired
	private CustomerNewOldInfoMapper customerNewOldInfoMapper;
	
	@Autowired
	private NewCustomerActivateMapper newCustomerActivateMapper;
	
	@Autowired
	private CustomerOrderQueryMapper customerOrderQueryMapper;
	
	@Override
	public int loadNewCustomerBindNoActivate(Integer day){
		
		List<NewCustomerActivate> bindNoActivate = customerNewOldInfoMapper.loadNewCustomerBindNoActivate(day);
		if(bindNoActivate == null || bindNoActivate.isEmpty()){
			return 0;
		}
		return newCustomerActivateMapper.insertBatch(bindNoActivate);
	}

	@Override
	public int loadNewCustomerActivateNoRemain(Integer day) {
		//更新激活留存
		int activateRemain = customerNewOldInfoMapper.updateActivateRemain(day);
		logger.info("updateActivateRemain row {}", activateRemain);
		//激活未留存
		List<NewCustomerActivate> customerBindNoRemain = customerNewOldInfoMapper.loadNewCustomerBindNoRemain(day);
		if(customerBindNoRemain != null && !customerBindNoRemain.isEmpty()){
			logger.info("updateActivateRemain insert row {}", customerBindNoRemain.size());
			newCustomerActivateMapper.insertBatch(customerBindNoRemain);
		}
		//激活未留存变成老商户
		int activateNoRemain = customerNewOldInfoMapper.updateActivateNoRemain(day);
		logger.info("updateActivateRemain row {}", activateNoRemain);
		
		return activateRemain + activateNoRemain;
	}

	@Override
	public List<NewCustomerActivate> findAllList(Page<List<NewCustomerActivate>> page, Map<String, String> param) {
		return newCustomerActivateMapper.findAllList(page, param);
	}

	@Override
	public int loadNewCustomerActivateTrans(Integer day) {
		int start = 0;
		int pageLimit = 1000;
		int sum = 0;
		for(int i=1; i <= day; i++){
			while(true){
				List<String> customerNos = newCustomerActivateMapper.findCustomerByDay(i,start,pageLimit);
				start = start + pageLimit;
				if(customerNos != null && !customerNos.isEmpty()){
					logger.info("findCustomerByDay customerNos.size {} ", customerNos.size());
					List<Map<String,Object>> list = customerOrderQueryMapper.findTransTimeByTime(i, customerNos);
					if(list== null || list.isEmpty()){
						logger.info("findTrans is empty");
						continue;
					}
					int batch = newCustomerActivateMapper.updateNewCustomerActivateTrans(i,list);
					sum = sum + batch;
					logger.info( "insert row {} {} ",batch, sum);
					if(customerNos.size() != pageLimit){
						//如果和总条数不相等 说明最后一页
						start = 0;
						break;
					}
				}else {
					start = 0;
					break;
				}
			}
		}
		
		return sum;
	}
}
