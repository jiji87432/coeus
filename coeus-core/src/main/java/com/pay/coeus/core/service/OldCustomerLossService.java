package com.pay.coeus.core.service;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.OldCustomerLoss;
import com.pay.commons.utils.Page;

public interface OldCustomerLossService {
	
	public int loadSuspectCustomerLoss(Integer beginDay, Integer endDay);

	public int loadCustomerLoss();
	
	public List<OldCustomerLoss> findAllOldCustomer(Page<List<OldCustomerLoss>> page,Map<String,String> param);
	
	/**
	 * 激活留存是否交易
	 * @param day
	 * @return
	 */
	public int loadOldCustomerActivateTrans(Integer day);

}
