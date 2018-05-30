package com.pay.coeus.core.quartz.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pay.coeus.core.service.CustomerNewOldInfoService;
import com.pay.dsp.common.biz.model.ReturnT;
import com.pay.dsp.core.handler.BaseJobHandler;
import com.pay.dsp.core.handler.annotation.JobHander;

/**
 * <p>Title:CustomerActivateTimeLoadHandler</p>
 * <p>Description:商户激活时间发送mq</p>
 * @author yongda.ren
 * @date 2018年1月15日 上午3:27:56
 */
@JobHander(value = "customerActivateTimeLoadHandler")
@Component
public class CustomerActivateTimeLoadHandler extends BaseJobHandler {
	
	Logger logger = LoggerFactory.getLogger(CustomerActivateTimeLoadHandler.class);
	@Autowired
	private CustomerNewOldInfoService customerNewOldInfoService;

	@Override
	public ReturnT<String> execute(String... arg0) throws Exception {
		try {
			//新商户激活时间
			int count = customerNewOldInfoService.loadCustomerActivateTimeDay();
			logger.info("CustomerActivateTimeLoadHandler success {}", count);
		} catch (Exception e) {
			logger.error("CustomerActivateTimeLoadHandler error", e);
		}
		return ReturnT.SUCCESS;
	}

}
