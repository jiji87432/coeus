package com.pay.coeus.core.quartz.handler;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pay.coeus.common.utils.DateUtils;
import com.pay.coeus.core.service.CustomerDailyCountService;
import com.pay.dsp.common.biz.model.ReturnT;
import com.pay.dsp.core.handler.BaseJobHandler;
import com.pay.dsp.core.handler.annotation.JobHander;

/**
 * 统计日活
 * @Description: 这里用一句话描述这个类的作用
 * @see: AppPushIdSyncJob 此处填写需要参考的类
 * @version 2017年11月6日 上午7:37:52 
 * @author yuze.luo
 */
@JobHander(value = "customerDailyCountHandler")
@Component
public class CustomerDailyCountHandler extends BaseJobHandler {
	
	private Logger logger = LoggerFactory.getLogger(CustomerDailyCountHandler.class);
	@Resource
	private CustomerDailyCountService customerDailyCountService;
	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		String date = DateUtils.getFixedDays(DateUtils.getDate(), "yyyy-MM-dd", -1);
		logger.info("CustomerDailyCountJob_start,date:{},time:{}", date, DateUtils.getDateTime());
		try {
			customerDailyCountService.countCustomerDaily(date);
		} catch (Exception e) {
			logger.error("CustomerDailyCountJob_统计日活失败", e);
		}
		logger.info("CustomerDailyCountJob_end,time:{}", DateUtils.getDateTime());
		return ReturnT.SUCCESS;
	}
}
