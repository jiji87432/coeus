package com.pay.coeus.core.quartz.handler;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pay.coeus.common.utils.DateUtils;
import com.pay.coeus.core.service.SimFeeDeductionService;
import com.pay.dsp.common.biz.model.ReturnT;
import com.pay.dsp.core.handler.BaseJobHandler;
import com.pay.dsp.core.handler.annotation.JobHander;

/**
 * sim卡扣费
 * @Description: 这里用一句话描述这个类的作用
 * @see: SimFeeDeductionHandler 此处填写需要参考的类
 * @version 2018年2月1日 上午2:01:51 
 * @author yuze.luo
 */
@JobHander(value = "simFeeDeductionHandler")
@Component
public class SimFeeDeductionHandler extends BaseJobHandler {
	
	private Logger logger = LoggerFactory.getLogger(SimFeeDeductionHandler.class);
	
	@Resource
	private SimFeeDeductionService simFeeDeductionService;
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		logger.info("start,time:{}", DateUtils.getDateTime());
		// 需要扣费的放到扣费表
		simFeeDeductionService.createFreezeSimDeduct();
		logger.info("half1,time:{}", DateUtils.getDateTime());
		// 调扣费接口未成功的重新调。注销掉是因为老数据有的商户已经迁移了。
//		simFeeDeductionService.modifyWaitDeductToPreereeze();
//		logger.info("half2,time:{}", DateUtils.getDateTime());
		// 查询修改未成功扣费的扣费状态
		simFeeDeductionService.updateSimDeductByQueryFareStatus();
		logger.info("end,time:{}", DateUtils.getDateTime());
		return ReturnT.SUCCESS;
	}

}
