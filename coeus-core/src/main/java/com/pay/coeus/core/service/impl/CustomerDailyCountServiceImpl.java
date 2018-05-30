package com.pay.coeus.core.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pay.coeus.common.utils.DateUtils;
import com.pay.coeus.common.utils.StringUtils;
import com.pay.coeus.core.dao.coeus.CustomerDailyCountMapper;
import com.pay.coeus.core.service.CustomerDailyCountService;
import com.pay.coeus.model.entity.CustomerDailyCount;
import com.pay.commons.utils.Page;
import com.pay.customer.remote.service.CustomerServiceFacade;
import com.pay.wx.dubbo.AppOperationOuterFacade;
import com.pay.wx.dubbo.MenuLogOuterFacade;
import com.pay.wx.dubbo.WxCustomerOuterServiceFacade;

/** 
 * @Description: 日活统计
 * @see: CustomerContactInfoService 此处填写需要参考的类
 * @version 2017年11月9日 上午6:54:54 
 * @author yuze.luo
 */
@Service
public class CustomerDailyCountServiceImpl implements CustomerDailyCountService{
	private Logger logger = LoggerFactory.getLogger(CustomerDailyCountServiceImpl.class);
	@Resource
    private CustomerDailyCountMapper customerDailyCountMapper;
	@Resource
	private MenuLogOuterFacade menuLogOuterFacade;
	@Resource
	private AppOperationOuterFacade appOperationOuterFacade;
	@Resource
	private CustomerServiceFacade customerServiceFacade;
	@Resource
	private WxCustomerOuterServiceFacade wxCustomerOuterServiceFacade;
	
	@Override
	public void countCustomerDaily(String date) {
		CustomerDailyCount dc = customerDailyCountMapper.getByDate(date);
		if(StringUtils.isBlank(date)){
			date = DateUtils.getFixedDays(DateUtils.getDate(), "yyyy-MM-dd", -1);
		}
		String strTime = date + " 00:00:00";
		String endTime = date + " 23:59:59";
		if(dc == null){
			logger.info(date+",今日没有统计");
			dc = new CustomerDailyCount();
		}else{
			logger.info(date+",今日已有统计");
		}
		if(dc.getApp() == null || dc.getApp() == -1){
			try {
				int app = appOperationOuterFacade.getAppOperationCount(strTime, endTime);
				dc.setApp(app);
			} catch (Exception e) {
				logger.error("查询menuLogOuterFacade.getWeixinCount异常",e);
				dc.setApp(-1);
			}
		}
		if(dc.getWeixin() == null || dc.getWeixin() == -1){
			try {
				int weixin = menuLogOuterFacade.getWeixinCount(strTime, endTime);
				dc.setWeixin(weixin);
			} catch (Exception e) {
				logger.error("查询appOperationOuterFacade.getAppOperationCount异常",e);
				dc.setWeixin(-1);
			}
		}
		//商户后台活跃度
		if(dc.getMerchant() == null || dc.getMerchant() == -1){
			try {
				int merchant = customerServiceFacade.countLogin(strTime, endTime);
				dc.setMerchant(merchant);
			} catch (Exception e) {
				logger.error("查询商户后台活跃度异常",e);
				dc.setMerchant(-1);
			}
		}
		if(dc.getWeixinAll() == null || dc.getWeixinAll() == -1 ){
			try {
				Map<String, Object> weixinMap = wxCustomerOuterServiceFacade.getWeixinBindCount(endTime);
				dc.setWeixinAll(Integer.valueOf(weixinMap.get("weixinAll").toString()));
				dc.setWeixinPos(Integer.valueOf(weixinMap.get("weixinPos").toString()));
			} catch (Exception e) {
				logger.error("查询appOperationOuterFacade.getAppOperationCount异常",e);
				dc.setWeixinAll(-1);
				dc.setWeixinPos(-1);
			}
		}
		if(dc.getId() == null || dc.getId() == 0){
			// 新增
			dc.setDailyDate(date);
			customerDailyCountMapper.insert(dc);
		}else{
			// 修改
			customerDailyCountMapper.update(dc);
		}
	}

	@Override
	public List<CustomerDailyCount> findAllLList(Page<List<CustomerDailyCount>> page, Map<String, String> param) {
		return customerDailyCountMapper.findAllList(page, param);
	}
	
}
