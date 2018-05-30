package com.pay.coeus.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.core.dao.boss.CustomerOrderQueryMapper;
import com.pay.coeus.core.dao.boss.MonthTradeFrequencyMapper;
import com.pay.coeus.core.dao.coeus.OldCustomerLossMapper;
import com.pay.coeus.core.service.OldCustomerLossService;
import com.pay.coeus.model.entity.NewCustomerActivate;
import com.pay.coeus.model.entity.OldCustomerLoss;
import com.pay.commons.utils.Page;

/**
 * 
 * <p>Title:OldCustomerLossServiceImpl</p>
 * <p>Description:老商户流失Service层</p>
 * @author yongda.ren
 * @date 2017年10月25日 下午3:43:36
 */
@Service
public class OldCustomerLossServiceImpl implements OldCustomerLossService {
	
	Logger logger = LoggerFactory.getLogger(OldCustomerLossServiceImpl.class);
	@Autowired
	private MonthTradeFrequencyMapper monthTradeFrequencyMapper;
	
	@Autowired
	private OldCustomerLossMapper oldCustomerLossMapper;
	
	@Autowired
	private CustomerOrderQueryMapper customerOrderQueryMapper;

	@Override
	public int loadSuspectCustomerLoss(Integer beginDay, Integer endDay) {
		
		int start = 1;
		int pageLimit = 999;
		int sum = 0;
		while(true){
			
			List<OldCustomerLoss> customerLoss = monthTradeFrequencyMapper.loadSuspectCustomerLoss2(beginDay, endDay, start, start+pageLimit);
			if(customerLoss != null && !customerLoss.isEmpty()){
				logger.info("customerLoss.size {} ", customerLoss.size());
				int batch = oldCustomerLossMapper.insertBatch(customerLoss);
				sum = sum + batch;
				logger.info( "insert row {} {} ",batch, sum);
				if(customerLoss.size() != (pageLimit+1)){
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

	@Override
	public int loadCustomerLoss() {
		
		//List<OldCustomerLoss> customerLoss = monthTradeFrequencyMapper.loadCustomerLoss();
		int start = 1;
		int pageLimit = 999;
		int sum = 0;
		while(true){
			
			List<OldCustomerLoss> customerLoss2 = monthTradeFrequencyMapper.loadCustomerLoss2(start, start+pageLimit);
			if(customerLoss2 != null && !customerLoss2.isEmpty()){
				logger.info("customerLoss2.size {} ", customerLoss2.size());
				int batch = oldCustomerLossMapper.insertBatch(customerLoss2);
				sum = sum + batch;
				logger.info( "insert row {} {} ",batch, sum);
				if(customerLoss2.size() != (pageLimit+1)){
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
	
	@Override
	public List<OldCustomerLoss> findAllOldCustomer(Page<List<OldCustomerLoss>> page, Map<String, String> param) {
		return oldCustomerLossMapper.findAllList(page, param);
	}

	@Override
	public int loadOldCustomerActivateTrans(Integer day) {
		int start = 0;
		int pageLimit = 1000;
		int sum = 0;
		for(int i=1; i <= day; i++){
			while(true){
				List<String> customerNos = oldCustomerLossMapper.findCustomerByDay(i,start,pageLimit);
				start = start + pageLimit;
				if(customerNos != null && !customerNos.isEmpty()){
					logger.info("findCustomerByDay customerNos.size {} ", customerNos.size());
					List<Map<String,Object>> list = customerOrderQueryMapper.findTransTimeByTime(i, customerNos);
					if(list== null || list.isEmpty()){
						logger.info("findTrans is empty");
						continue;
					}
					int batch = oldCustomerLossMapper.updateOldCustomerActivateTrans(i,list);
					sum = sum + batch;
					logger.info( "update row {} {} ",batch, sum);
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
