package com.pay.coeus.core.quartz.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pay.coeus.core.service.OldCustomerLossService;
import com.pay.dsp.common.biz.model.ReturnT;
import com.pay.dsp.core.handler.BaseJobHandler;
import com.pay.dsp.core.handler.annotation.JobHander;

/**
 * 
 * <p>Title:CustomerTransInfoJob</p>
 * <p>Description:预警后商户交易状态</p>
 * @author yongda.ren
 * @date 2017年11月15日 上午11:35:46
 */
@JobHander(value = "oldCustomerTransInfoHandler")
@Component
public class OldCustomerTransInfoHandler extends BaseJobHandler {
	
	private Logger logger = LoggerFactory.getLogger(OldCustomerTransInfoHandler.class);

	@Autowired
	private OldCustomerLossService oldCustomerLossService;
	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		int customerActivateTrans = oldCustomerLossService.loadOldCustomerActivateTrans(30);
		logger.info("loadNewCustomerActivateTrans row {}", customerActivateTrans);
		return ReturnT.SUCCESS;
	}

}
