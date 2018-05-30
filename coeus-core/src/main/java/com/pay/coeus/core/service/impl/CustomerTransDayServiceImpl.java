package com.pay.coeus.core.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pay.coeus.core.dao.boss.CustomerMapper;
import com.pay.coeus.core.dao.boss.CustomerTransDayMapper;
import com.pay.coeus.core.service.CustomerTransDayService;
import com.pay.coeus.model.entity.CustomerTransDay;
import com.pay.commons.utils.Page;

/**
 * 日交易汇总查询服务
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerTransDayServiceImpl 此处填写需要参考的类
 * @version 2017年11月21日 上午6:45:04 
 * @author yuze.luo
 */
@Service
public class CustomerTransDayServiceImpl implements CustomerTransDayService{
	private Logger logger = LoggerFactory.getLogger(CustomerTransDayServiceImpl.class);
	@Resource
    private CustomerTransDayMapper customerTransDayMapper;
	@Resource
    private CustomerMapper customerMapper;
	@Override
	public Map<String, String> getActiveCustomerData(String strTime, String endTime) {
		logger.info("getActiveCustomerData:"+strTime+","+endTime);
		Map<String, String> result = customerTransDayMapper.getActiveCustomerData(strTime, endTime);
		logger.info("getActiveCustomerData_result:"+result.toString());
		return result;
	}
	
	@Override
	public Map<String, String> getNewCustomerData(String strTime, String endTime) {
		logger.info("getNewCustomerData:"+strTime+","+endTime);
		Map<String, String> returnMap = new HashMap<String, String>();
		int NEWNUM = customerMapper.getByOpenTime(strTime, endTime);
		returnMap.put("NEWNUM", NEWNUM+"");
		Map<String, String> result = customerTransDayMapper.getNewCustomerData(strTime, endTime);
		returnMap.putAll(result);
		logger.info("getNewCustomerData_result:"+returnMap);
		return returnMap;
	}

	@Override
	public List<CustomerTransDay> findAllList(Page<List<CustomerTransDay>> page, Map<String, String> param) {
		return customerTransDayMapper.findAllList(page, param);
	}

	@Override
	public List<Map<String, String>> findAllCountList(Page<List<Map<String, String>>> page, Map<String, String> param) {
		return customerTransDayMapper.findAllCountList(page, param);
	}
	
}
