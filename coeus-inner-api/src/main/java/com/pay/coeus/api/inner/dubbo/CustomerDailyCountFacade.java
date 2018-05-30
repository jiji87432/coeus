package com.pay.coeus.api.inner.dubbo;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.CustomerDailyCount;
import com.pay.commons.utils.Page;

/**
 * 平台日活统计
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerDailyCountFacade 此处填写需要参考的类
 * @version 2017年11月13日 上午2:36:38 
 * @author yuze.luo
 */
public interface CustomerDailyCountFacade {

	Page<List<CustomerDailyCount>> findAllList(Page<List<CustomerDailyCount>> page, Map<String, String> param);
	
}
