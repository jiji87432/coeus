/**
 * 
 */
package com.pay.coeus.portal.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pay.coeus.api.inner.dubbo.CommonScriptServiceFacade;


/**
 * 公共查询方法
 * @Description: 这里用一句话描述这个类的作用
 * @see: CommonScriptController 此处填写需要参考的类
 * @version 2017年10月27日 上午1:34:53 
 * @author yuze.luo
 */
@Controller
@RequestMapping("/commonScript")
public class CommonScriptController {

	private static final Logger logger = LoggerFactory.getLogger(CommonScriptController.class);

	@Resource
	private CommonScriptServiceFacade commonScriptServiceFacade;

	@RequestMapping(value = "/toCommonQuery.action")
	public String initPage() {
		return "common/commonQuery";
	}

	@RequestMapping(value = "/commonQuery.action")
	public String query(@RequestParam("sqlScript") String sqlScript) {
		String sql = sqlScript.trim();
		sql = sql.replace("\n", "");
		sql = sql.replace("\r", "");
		logger.info("commonQuery sql : [{}]", sql);
		// String upperSql = sql.toUpperCase();
		String[] attrSql = sql.split(";");
		for (int i = 0; i < attrSql.length; i++) {
			logger.info("commonQuery final sql:{}", attrSql[i]);
			attrSql[i] = attrSql[i].trim();
			String finalsql = attrSql[i].trim().toUpperCase();
			if ((finalsql.startsWith("UPDATE ") && finalsql.indexOf("WHERE") > -1) || finalsql.startsWith("INSERT ")) {
				try {
					commonScriptServiceFacade.updateScript(attrSql[i]);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} else {
				throw new RuntimeException("不允许执行");
			}
		}

		return "redirect:/commonScript/toCommonQuery.action";
	}

}
