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
 * <p>Title:CustomerNewOldInfoJob</p>
 * <p>Description:新老商户沉淀</p>
 * @author yongda.ren
 * @date 2017年10月24日 下午5:40:41
 */
@JobHander(value = "customerNewOldInfoHandler")
@Component
public class CustomerNewOldInfoHandler extends BaseJobHandler {
	
	Logger logger = LoggerFactory.getLogger(CustomerNewOldInfoHandler.class);

	@Autowired
	private CustomerNewOldInfoService customerNewOldInfoService;
	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		logger.info("CustomerNewOldInfoJob in");
		try {
			int newOpen = customerNewOldInfoService.loadCustomerNewOpen();
			logger.info("CustomerNewOldInfoJob success count {}", newOpen);
//			Long incr = RedisUtil.incr("COEUS_CustomerNewOldInfoJob", 60*60*12);
//			logger.info("COEUS_CustomerNewOldInfoJob incr {}", incr); //加入完成后添加标记
		} catch (Exception e) {
			logger.error("CustomerNewOldInfoJob error ", e);
		}
		return ReturnT.SUCCESS;
	}
	
}
