package com.pay.coeus.core.service.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.coeus.core.service.CommonScriptService;

/** 
 * @Description: 这里用一句话描述这个类的作用
 * @see: CommonScriptService 此处填写需要参考的类
 * @version 2017年6月9日 上午6:54:54 
 * @author shulin.feng
 */
@Service
public class CommonScriptServiceImpl implements CommonScriptService{
	
	@Resource
    private JdbcTemplate jdbcTemplate;

	@Transactional(propagation = Propagation.REQUIRED,transactionManager="dataSourceTransactionManager", rollbackFor = Exception.class)
	public void updateScript(String sql) {
		jdbcTemplate.execute(sql);
	}
}
