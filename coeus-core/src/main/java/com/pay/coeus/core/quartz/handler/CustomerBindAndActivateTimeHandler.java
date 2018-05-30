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
 * 
 * <p>Title:CustomerBindAndActivateTimeJob</p>
 * <p>Description:绑定激活时间统计</p>
 * @author yongda.ren
 * @date 2017年10月24日 下午5:41:09
 */
@JobHander(value = "customerBindAndActivateTimeHandler")
@Component
public class CustomerBindAndActivateTimeHandler extends BaseJobHandler {
	
	Logger logger = LoggerFactory.getLogger(CustomerBindAndActivateTimeHandler.class);

	@Autowired
	private CustomerNewOldInfoService customerNewOldInfoService;
	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		//Long incr = RedisUtil.incr("COEUS_CustomerNewOldInfoJob", 60*60*12);
		//logger.info("BindAndActivateTimeJob in incr {}", incr);
		//if(incr != 1){//判断前一天新入网商户有没有加入
			try {
				//更新绑定和激活时间
				customerNewOldInfoService.updateBindAndActivateTime();
				logger.info("updateBindAndActivateTime success");
			} catch (Exception e) {
				logger.error("updateBindAndActivateTime error", e);
			}
		//} else{
			//logger.info("COEUS_CustomerNewOldInfoJob not success ");
		//}
			return ReturnT.SUCCESS;
	}

}
