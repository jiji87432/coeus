package com.pay.coeus.core.dao.boss;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CommunityInformationMapper {

	/**
	 * 查询商编 商户名称 商户状态 商户等级 服务商编号 服务商名称 真实性标签 月交易额
	 */
	Map<String, String> CommunityInformatQuery(@Param("customerNo")String customerNo); 
	
	/**
	 * 判断此商户是否为大POS商户
	 */
	String isBigPos(@Param("customerNo")String customerNo);
	
	/**
	 * 查询商户月交易
	 */
	String monthSumAmount(@Param("customerNo")String customerNo,@Param("yearv")String yearv,@Param("periodv")String periodv);
}
