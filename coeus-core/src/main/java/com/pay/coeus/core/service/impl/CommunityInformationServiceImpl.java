package com.pay.coeus.core.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pay.coeus.core.dao.boss.CommunityInformationMapper;
import com.pay.coeus.core.service.CommunityInformationService;

@Service
public class CommunityInformationServiceImpl implements CommunityInformationService{

	private static final Logger logger = LoggerFactory.getLogger(CommunityInformationServiceImpl.class);
	
	@Resource
	private CommunityInformationMapper communityInformationMapper;
	
	@Override
	public Map<String, String> CommunityInformatQuery(String customerNo,String orderTime) {
		
		//获取所传交易时间的 年  月
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM");
		Date parse = null;
		try {
			parse = formatter.parse(orderTime);
		} catch (ParseException e) {
			logger.error("Date 转换异常",e);
		}
		Calendar now = Calendar.getInstance();
		now.setTime(parse);
		int yearv = now.get(Calendar.YEAR);
		int periodv = now.get(Calendar.MONTH)+1;
		String periodvTemp = String.valueOf(periodv);
		if(periodvTemp.length()!=2){
			periodvTemp = "0".concat(periodvTemp);
		}
		//获取 商编 商户名称 商户状态 商户等级 服务商编号 服务商名称 真实性标签 月交易额
		Map<String, String> communityInformatQuery = communityInformationMapper.CommunityInformatQuery(customerNo);
		if(communityInformatQuery != null){
			logger.info(" 查询时间year:{},periodv:{}",yearv,periodvTemp);
			//查询商户月交易总和
			String monthSumAmount = communityInformationMapper.monthSumAmount(customerNo,String.valueOf(yearv), periodvTemp);
			if(null != monthSumAmount ){
				communityInformatQuery.put("sumamount",monthSumAmount);
			}else{
				communityInformatQuery.put("sumamount",String.valueOf(0));
			}
			//查询是否大POS商户
			String isNo = communityInformationMapper.isBigPos(customerNo);
			if(isNo == null || isNo.isEmpty()){
				communityInformatQuery.put("isPos", "否");
			}else {
				communityInformatQuery.put("isPos", "是");
			}
			return communityInformatQuery;
		}
		return null;
		
	}

}
